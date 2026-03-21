package com.nikhilsable.backend.controller;

import com.nikhilsable.backend.dto.UserRegisterRequest;
import com.nikhilsable.backend.dto.UserResponse;
import com.nikhilsable.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequest request) {
        UserResponse user = userService.register(request);
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("_id", user.getId());
        userMap.put("email", user.getEmail());
        userMap.put("name", user.getName());
        body.put("user", userMap);
        return ResponseEntity.ok(body);
    }
}

