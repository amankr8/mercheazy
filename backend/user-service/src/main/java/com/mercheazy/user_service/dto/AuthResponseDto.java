package com.mercheazy.user_service.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String username;
    private String role;
}
