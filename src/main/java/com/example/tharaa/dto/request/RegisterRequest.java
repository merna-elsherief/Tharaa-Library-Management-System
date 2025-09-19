package com.example.tharaa.dto.request;

import com.example.tharaa.domain.enums.Role;

public record RegisterRequest(
        String username,
        String password,
        Role role
) {}
