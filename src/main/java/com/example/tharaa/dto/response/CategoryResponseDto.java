package com.example.tharaa.dto.response;

import java.util.Set;

public record CategoryResponseDto(
        Long id,
        String name,
        CategoryParentDto parentCategory,
        Set<CategoryResponseDto> subCategories
) {}
