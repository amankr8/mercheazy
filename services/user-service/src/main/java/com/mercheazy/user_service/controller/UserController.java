package com.mercheazy.user_service.controller;

import com.mercheazy.user_service.dto.UserRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/users")
public interface UserController {

    @GetMapping
    ResponseEntity<?> getAllUsers();

    @GetMapping("/{userId}")
    ResponseEntity<?> getUserById(@PathVariable Long userId);

    @GetMapping("/email")
    ResponseEntity<?> getUserByEmail(@RequestParam String email);

    @PostMapping
    ResponseEntity<?> createUser(@RequestBody UserRequestDto userRequestDto);

    @PutMapping("/{userId}")
    ResponseEntity<?> updateUser(@PathVariable Long userId, UserRequestDto userRequestDto);

    @DeleteMapping("/{userId}")
    ResponseEntity<?> deleteUser(@PathVariable Long userId);
}
