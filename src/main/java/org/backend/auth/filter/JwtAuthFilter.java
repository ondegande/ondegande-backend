package org.backend.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.backend.auth.application.AuthService;
import org.backend.auth.application.JwtUtils;
import org.backend.member.application.MemberService;
import org.backend.member.domain.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtAuthFilter extends GenericFilterBean {

    private final JwtUtils jwtUtil;
    private final AuthService authService;
    private final MemberService memberService;

    public JwtAuthFilter(JwtUtils jwtUtil,
                         AuthService authService,
                         MemberService memberService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
        this.memberService = memberService;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        String accessToken = jwtUtil.extractAccesToken((HttpServletRequest) request).orElse(null);
        String refreshToken = jwtUtil.extractRefreshToken((HttpServletRequest) request).orElse(null);

        if (accessToken != null && jwtUtil.verifyToken(accessToken)) {
            Authentication auth = authService.getAuthentication(jwtUtil.getUid(accessToken), jwtUtil.getRole(accessToken));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        else if(refreshToken != null) {
            Member member = memberService.findByRefreshToken(refreshToken);
            if(member != null) {
                jwtUtil.reIssueToken((HttpServletResponse) response, member.getEmail());
                Authentication auth = authService.getAuthentication(member.getEmail(), member.getRole());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}
