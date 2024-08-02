package org.backend.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Date;
import java.util.Optional;
import org.backend.auth.jwt.Token;
import org.backend.member.domain.Role;
import org.springframework.beans.factory.annotation.Value;

public class JwtUtils {

    private Key secretKey;

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;

    @Value("${jwt.access.expiration}")
    private long TOKEN_PERIOD;

    @Value("${jwt.refresh.expiration}")
    private long REFRESH_PERIOD;

    private static final String SERVER_ISSUER = "SERVER";
    private static final String ROLE = "role";
    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private static final String AUTHORIZATION_REFRESH_TOKEN = "refreshToken";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    @PostConstruct
    public void init() {
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SECRET_KEY));
    }

    public Token createToken(String email) {
        Claims claims = Jwts.claims()
                .setIssuer(SERVER_ISSUER)
                .setAudience(email);
        claims.put(ROLE, Role.USER.getKey());

        return new Token(createAccessToken(claims), createRefreshToken(claims));
    }

    public String createAccessToken(Claims claims) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(ACCESS_TOKEN_SUBJECT)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_PERIOD))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken(Claims claims) {
        claims.remove(ROLE);
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(REFRESH_TOKEN_SUBJECT)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_PERIOD))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean verifyToken(String token) {
        try{
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey).build().parseClaimsJws(token);
            return claims.getBody().getExpiration()
                    .after(new Date());
        } catch (UnsupportedJwtException e) {
            System.out.println("지원하지 않는 JWT입니다.");
            return false;
        } catch (MalformedJwtException e) {
            System.out.println("잘못된 JWT 서명입니다.");
            return false;
        } catch (SignatureException e) {
            System.out.println("토큰의 서명 유효성 검사가 실패했습니다.");
            return false;
        } catch (ExpiredJwtException e) {
            System.out.println("토큰의 유효기간이 만료되었습니다.");
            return false;
        } catch (Exception e) {
            System.out.println("알 수 없는 토큰 유효성 문제가 발생했습니다." + e.getMessage());
            return false;
        }
    }

    public String getUid(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().getAudience();
    }

    public String getRole(String token) {
        String role = Jwts.parserBuilder()
                .setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().get("role").toString();

        return role;
    }

    public Optional<String> extractAccesToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(AUTHORIZATION))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(AUTHORIZATION_REFRESH_TOKEN))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    public Token reIssueToken(HttpServletResponse response, String email) {
        Token token = createToken(email);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader(AUTHORIZATION, BEARER + token.getAccessToken());
        response.setHeader(AUTHORIZATION_REFRESH_TOKEN, BEARER + token.getRefreshToken());
        return token;
    }
}
