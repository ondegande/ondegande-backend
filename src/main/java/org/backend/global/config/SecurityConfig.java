package org.backend.global.config;

import org.backend.auth.application.AuthService;
import org.backend.auth.jwt.JwtUtils;
import org.backend.auth.filter.JwtAuthFilter;
import org.backend.member.application.MemberService;
import org.backend.member.domain.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtils jwtUtils;
    private final AuthService authService;
    private final MemberService memberService;

    private static final String[] NON_AUTH_LIST = {
            "/",
            "/swagger-ui/**",
            "/token/**",
            "/h2-console/**"
    };

    private static final String[] AUTH_LIST = {
            "/api/**"
    };

    public SecurityConfig(JwtUtils jwtUtils,
                          AuthService authService,
                          MemberService memberService) {
        this.jwtUtils = jwtUtils;
        this.authService = authService;
        this.memberService = memberService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement((sessionManagement) -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui/**", "/swagger-ui", "/api/swagger-docs", "/api/hello").permitAll()
                        .requestMatchers("/api/**").permitAll()  //hasRole(Role.USER.name())
                        .anyRequest().permitAll())
                .addFilterBefore(jwtAuthFilter(jwtUtils, authService, memberService), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(JwtUtils jwtUtils,
                                       AuthService authService,
                                       MemberService memberService) {
        return new JwtAuthFilter(
                jwtUtils,
                authService,
                memberService);
    }
}
