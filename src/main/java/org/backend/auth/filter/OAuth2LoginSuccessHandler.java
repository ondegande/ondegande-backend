package org.backend.auth.filter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.backend.auth.jwt.JwtUtils;
import org.backend.auth.CustomOauth2User;
import org.backend.auth.jwt.Token;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtils jwtUtil;

    public OAuth2LoginSuccessHandler(JwtUtils jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        CustomOauth2User oAuth2User = (CustomOauth2User) authentication.getPrincipal();

        Token token = jwtUtil.createToken(oAuth2User.getEmail());

        String targetUrl = UriComponentsBuilder.fromUriString("/")
                .queryParam("signup", token)
                .build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
