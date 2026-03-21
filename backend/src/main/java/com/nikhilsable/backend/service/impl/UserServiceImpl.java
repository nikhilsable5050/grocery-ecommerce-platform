package com.nikhilsable.backend.service.impl;

import com.nikhilsable.backend.dto.UserRegisterRequest;
import com.nikhilsable.backend.dto.UserResponse;
import com.nikhilsable.backend.model.User;
import com.nikhilsable.backend.repository.UserRepository;
import com.nikhilsable.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(UserRegisterRequest request) {
        // check if user exists
        userRepository.findByEmail(request.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("email already in use");
        });

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // store encrypted
        user.setCartItems(new HashMap<>());

        User saved = userRepository.save(user);

        return new UserResponse(saved.getId(), saved.getEmail(), saved.getName());
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }
}
