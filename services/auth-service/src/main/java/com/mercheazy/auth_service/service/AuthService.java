package com.mercheazy.auth_service.service;

import com.mercheazy.auth_service.model.AuthUser;
import com.mercheazy.auth_service.dto.LoginRequestDto;
import com.mercheazy.auth_service.dto.SignupRequestDto;

public interface AuthService {

    AuthUser signup(SignupRequestDto signupRequestDto);

    AuthUser login(LoginRequestDto loginRequestDto);

    AuthUser validateToken(String token);
}
