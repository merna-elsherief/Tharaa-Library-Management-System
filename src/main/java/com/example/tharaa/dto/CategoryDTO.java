package com.example.tharaa.dto;

import com.example.tharaa.domain.entity.Category;

import java.util.Set;
import java.util.stream.Collectors;

public record CategoryDTO(
        Long id,
        String name,
        Set<CategoryDTO> subCategories
) {
    public CategoryDTO(Category category) {
        this(
                category.getId(),
                category.getName(),
                category.getSubCategories() != null ?
                        category.getSubCategories()
                                .stream()
                                .map(CategoryDTO::new)
                                .collect(Collectors.toSet()) : Set.of()
        );
    }
}

