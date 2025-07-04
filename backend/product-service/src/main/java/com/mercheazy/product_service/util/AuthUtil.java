package com.mercheazy.product_service.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {

    public static String getUserContext() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (email == null || email.isEmpty()) {
            throw new IllegalStateException("User is not logged in or email is not available.");
        }
        return email;
    }
}
