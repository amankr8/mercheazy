package com.mercheazy.auth_service.dto;

import lombok.Data;

@Data
public class AuthUserResponseDto {
    private String username;
    private String role;
}
