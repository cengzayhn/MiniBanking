package com.cengzayhn.mini_banking_api.controller;

import com.cengzayhn.mini_banking_api.common.response.ApiResponse;
import com.cengzayhn.mini_banking_api.dto.request.LoginRequestDTO;
import com.cengzayhn.mini_banking_api.dto.request.RegisterRequestDTO;
import com.cengzayhn.mini_banking_api.model.User;
import com.cengzayhn.mini_banking_api.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Management", description = "Endpoints for user registration and login")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ApiResponse<User> register(@Valid @RequestBody RegisterRequestDTO request) {
        User newUser = userService.register(request);
        return ApiResponse.created(newUser);
    }

    @Operation(summary = "Authenticate user and return JWT")
    @PostMapping("/login")
    public ApiResponse<String> login(@Valid @RequestBody LoginRequestDTO request) {
        String token = userService.login(request);
        return ApiResponse.success(token);
    }
}
