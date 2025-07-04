package com.mercheazy.auth_service.controller.impl;

import com.mercheazy.auth_service.controller.AuthController;
import com.mercheazy.auth_service.dto.AuthResponseDto;
import com.mercheazy.auth_service.model.AuthUser;
import com.mercheazy.auth_service.dto.LoginRequestDto;
import com.mercheazy.auth_service.dto.SignupRequestDto;
import com.mercheazy.auth_service.service.AuthService;
import com.mercheazy.auth_service.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<?> signup(SignupRequestDto signupRequestDto) {
        AuthUser authUser = authService.signup(signupRequestDto);
        return ResponseEntity.ok(new AuthResponseDto(null, authUser.toResponseDto(), "User registered successfully!"));
    }

    @Override
    public ResponseEntity<?> login(LoginRequestDto loginRequestDto) {
        AuthUser authUser = authService.login(loginRequestDto);
        String jwtToken = jwtService.generateToken(authUser.getUsername());
        return ResponseEntity.ok(new AuthResponseDto(jwtToken, authUser.toResponseDto(), "Login successful!"));
    }

    @Override
    public ResponseEntity<?> validate(String token) {
        AuthUser authUser = authService.validateToken(token);
        return ResponseEntity.ok(authUser);
    }
}
