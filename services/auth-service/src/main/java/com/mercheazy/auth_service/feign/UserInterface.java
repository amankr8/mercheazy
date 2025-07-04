package com.mercheazy.auth_service.feign;

import com.mercheazy.auth_service.dto.UserRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserInterface {

    @PostMapping("/api/users")
    ResponseEntity<?> createUser(@RequestBody UserRequestDto userRequestDto);
}
