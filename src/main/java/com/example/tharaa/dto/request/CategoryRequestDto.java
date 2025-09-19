package com.example.tharaa.dto.request;

public record CategoryRequestDto(
        String name,
        Long parentCategoryId
) {}