package com.mercheazy.auth_service.service;

import java.util.Map;

public interface JwtService {

    String extractUsername(String token);

    String generateToken(String username);

    String generateToken(Map<String, Object> extraClaims, String username);

    Long getExpirationTime();

    Boolean isTokenValid(String token);
}
