package com.example.tharaa.service;

import com.example.tharaa.dto.request.RegisterRequest;
import com.example.tharaa.dto.request.LoginRequest;
import com.example.tharaa.dto.response.AuthResponse;

public interface UserService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
