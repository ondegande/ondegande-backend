package org.backend.auth.application;

import java.util.List;
import org.backend.auth.dto.response.KakaoMemberResponse;
import org.backend.auth.dto.response.LoginResponse;
import org.backend.auth.jwt.JwtUtils;
import org.backend.auth.jwt.Token;
import org.backend.member.application.MemberService;
import org.backend.member.domain.Member;
import org.backend.member.domain.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final KakaoOauth2Client kakaoOauth2Client;
    private final JwtUtils jwtUtils;
    private final MemberService memberService;

    public AuthService(KakaoOauth2Client kakaoOauth2Client,
                       JwtUtils jwtUtils,
                       MemberService memberService) {
        this.kakaoOauth2Client = kakaoOauth2Client;
        this.jwtUtils = jwtUtils;
        this.memberService = memberService;
    }

    public LoginResponse register(String code) {
        String accessToken = kakaoOauth2Client.getAccessToken(code);
        KakaoMemberResponse response = kakaoOauth2Client.getMemberInfo(accessToken);
        Member member = Member.from(response.kakaoAccount().email());
        Token token = jwtUtils.createToken(member.getEmail());
        Member registeredMember = memberService.register(member, token);
        return new LoginResponse(registeredMember.getEmail(), token);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String email, String role) {
        return new UsernamePasswordAuthenticationToken(email,
                "",
                List.of(new SimpleGrantedAuthority(role)));
    }
}
