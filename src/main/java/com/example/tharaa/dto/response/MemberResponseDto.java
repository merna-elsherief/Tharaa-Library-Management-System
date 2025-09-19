package com.example.tharaa.dto.response;

public record MemberResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address
) {}