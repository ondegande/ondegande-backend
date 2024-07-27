package org.backend.auth.application;

import java.util.List;
import org.backend.auth.dto.response.LoginResponse;
import org.backend.member.domain.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

//    public LoginResponse register(String code) {
//
//    }

    public UsernamePasswordAuthenticationToken getAuthentication(String email, Role role) {
        return new UsernamePasswordAuthenticationToken(email,
                "",
                List.of(new SimpleGrantedAuthority(role.getKey())));
    }
}
