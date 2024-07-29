package org.backend.auth.dto.response;

import org.backend.auth.jwt.Token;

public class LoginResponse {

    private String email;
    private String accessToken;
    private String refreshToken;

    public LoginResponse(String email, String accessToken, String refreshToken) {
        this.email = email;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public LoginResponse(String email, Token token) {
        this.email = email;
        this.accessToken = token.getAccessToken();
        this.refreshToken = token.getRefreshToken();
    }

    public String getEmail() {
        return email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
