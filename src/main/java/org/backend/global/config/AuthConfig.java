package org.backend.global.config;

import org.backend.auth.application.AuthService;
import org.backend.auth.application.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AuthConfig {

    @Bean
    public JwtUtils jwtUtil() {
        return new JwtUtils();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
