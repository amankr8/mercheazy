package com.mercheazy.auth_service.dto;

import com.mercheazy.auth_service.model.AuthUser;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthResponseDto {
    private String token;
    private AuthUserResponseDto user;
    private String message;
}
