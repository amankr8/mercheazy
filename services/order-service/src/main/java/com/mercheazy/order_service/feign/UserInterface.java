package com.mercheazy.order_service.feign;

import com.mercheazy.order_service.model.AppUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserInterface {

    @GetMapping("/api/users/email")
    ResponseEntity<AppUser> getUserByEmail(@RequestParam String email);
}
