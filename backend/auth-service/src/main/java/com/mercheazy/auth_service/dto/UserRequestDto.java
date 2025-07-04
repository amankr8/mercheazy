package com.mercheazy.auth_service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRequestDto {
    private String email;
}
