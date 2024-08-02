package org.backend.global.config;

import org.backend.auth.application.AuthService;
import org.backend.auth.application.CustomOAuth2UserService;
import org.backend.auth.jwt.JwtUtils;
import org.backend.auth.filter.JwtAuthFilter;
import org.backend.auth.filter.OAuth2LoginFailureHandler;
import org.backend.auth.filter.OAuth2LoginSuccessHandler;
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

    private final OAuth2LoginSuccessHandler oauth2SuccessHandler;
    private final OAuth2LoginFailureHandler oauth2FailureHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
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

    public SecurityConfig(OAuth2LoginSuccessHandler oauth2SuccessHandler,
                          OAuth2LoginFailureHandler oauth2FailureHandler,
                          CustomOAuth2UserService customOAuth2UserService,
                          JwtUtils jwtUtils,
                          AuthService authService,
                          MemberService memberService) {
        this.oauth2SuccessHandler = oauth2SuccessHandler;
        this.oauth2FailureHandler =oauth2FailureHandler;
        this.customOAuth2UserService = customOAuth2UserService;
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
                        .requestMatchers("/swagger-ui/**", "/swagger-ui").permitAll()
                        .requestMatchers("/api/**", "/hello").hasRole(Role.USER.name())
                        .anyRequest().permitAll())
                .oauth2Login(oauth2Login -> oauth2Login
                        .successHandler(oauth2SuccessHandler)
                        .failureHandler(oauth2FailureHandler)
                        .userInfoEndpoint(UserInfoEndpointConfig -> UserInfoEndpointConfig
                                .userService(customOAuth2UserService)))
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
