package com.mercheazy.auth_service.service.impl;

import com.mercheazy.auth_service.dto.UserRequestDto;
import com.mercheazy.auth_service.kafka.AuthProducer;
import com.mercheazy.auth_service.model.AuthUser;
import com.mercheazy.auth_service.dto.LoginRequestDto;
import com.mercheazy.auth_service.dto.SignupRequestDto;
import com.mercheazy.auth_service.repository.AuthUserRepository;
import com.mercheazy.auth_service.service.AuthService;
import com.mercheazy.auth_service.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthUserRepository authUserRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthProducer authProducer;

    @Override
    public AuthUser signup(SignupRequestDto signupRequestDto) {
        if (authUserRepository.findByUsername(signupRequestDto.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with email: " + signupRequestDto.getEmail());
        }

        UserRequestDto userRequestDto = UserRequestDto.builder()
                .email(signupRequestDto.getEmail())
                .build();

        authProducer.publishAuthEvent(userRequestDto);

        AuthUser newUser = AuthUser.builder()
                .username(signupRequestDto.getEmail())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .role(AuthUser.Role.USER)
                .enabled(true)
                .build();

        return authUserRepository.save(newUser);
    }

    @Override
    public AuthUser login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        return authUserRepository.findByUsername(loginRequestDto.getEmail())
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User not found"));
    }

    @Override
    public AuthUser validateToken(String token) {
        if (!jwtService.isTokenValid(token)) {
            throw new AuthorizationDeniedException("Invalid or expired token.");
        }

        String username = jwtService.extractUsername(token);
        return authUserRepository.findByUsername(username)
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User not found"));
    }
}
