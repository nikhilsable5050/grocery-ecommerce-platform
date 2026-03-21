package com.nikhilsable.backend.service;

import com.nikhilsable.backend.dto.UserRegisterRequest;
import com.nikhilsable.backend.dto.UserResponse;

public interface UserService {
    UserResponse register(UserRegisterRequest request);
}