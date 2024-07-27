package org.backend.auth.controller;

import org.backend.auth.application.AuthService;
import org.backend.auth.dto.response.LoginResponse;
import org.backend.global.response.ApiResponse;
import org.backend.global.response.ResponseCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//    @PostMapping("/auth/login/kakao")
//    public ApiResponse<LoginResponse> kakaoLogin(@RequestParam String code) {
//        return ApiResponse.success(ResponseCode.MEMBER_LOGIN_SUCCESS, authService.register(code));
//    }
}
