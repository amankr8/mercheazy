package com.mercheazy.auth_service.controller;

import com.mercheazy.auth_service.dto.LoginRequestDto;
import com.mercheazy.auth_service.dto.SignupRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
public interface AuthController {

    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody SignupRequestDto signupRequestDto);

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto);

    @GetMapping("/validate")
    ResponseEntity<?> validate(@RequestParam String token);
}
