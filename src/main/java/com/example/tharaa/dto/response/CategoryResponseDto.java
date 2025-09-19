package com.example.tharaa.dto.response;

public record CategoryResponseDto(
        Long id,
        String name,
        Long parentCategoryId
) {}
