package com.mercheazy.user_service.service.impl;

import com.mercheazy.user_service.dto.UserRequestDto;
import com.mercheazy.user_service.model.AppUser;
import com.mercheazy.user_service.repository.UserRepository;
import com.mercheazy.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppUser getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public AppUser getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    @Override
    public AppUser createUser(UserRequestDto userRequestDto) {
        AppUser newUser = new AppUser();
        newUser.setEmail(userRequestDto.getEmail());
        return userRepository.save(newUser);
    }

    @Override
    public AppUser updateUser(Long id, UserRequestDto userRequestDto) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
