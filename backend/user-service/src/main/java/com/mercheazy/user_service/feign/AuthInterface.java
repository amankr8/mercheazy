package com.mercheazy.user_service.feign;

import com.mercheazy.user_service.dto.AuthResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service")
public interface AuthInterface {

    @GetMapping("api/auth/validate")
    ResponseEntity<AuthResponseDto> validateToken(@RequestParam String token);
}
