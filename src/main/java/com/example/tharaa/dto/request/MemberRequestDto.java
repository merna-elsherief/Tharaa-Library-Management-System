package com.example.tharaa.dto.request;

public record MemberRequestDto(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address
) {}
