package com.mercheazy.user_service.service;

import com.mercheazy.user_service.dto.UserRequestDto;
import com.mercheazy.user_service.model.AppUser;

import java.util.List;

public interface UserService {

    List<AppUser> getAllUsers();

    AppUser getUserById(Long id);

    AppUser getUserByEmail(String email);

    AppUser createUser(UserRequestDto userRequestDto);

    AppUser updateUser(Long id, UserRequestDto userRequestDto);

    void deleteUser(Long id);
}
