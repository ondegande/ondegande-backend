package org.backend.global.config;

import org.backend.auth.application.AuthService;
import org.backend.auth.application.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Bean
    public JwtUtils jwtUtil() {
        return new JwtUtils();
    }

    @Bean
    public AuthService authService() {
        return new AuthService();
    }
}
