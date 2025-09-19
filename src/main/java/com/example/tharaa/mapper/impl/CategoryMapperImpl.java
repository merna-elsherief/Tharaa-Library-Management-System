package com.example.tharaa.mapper.impl;

import com.example.tharaa.domain.entity.Category;
import com.example.tharaa.dto.request.CategoryRequestDto;
import com.example.tharaa.dto.response.CategoryResponseDto;
import com.example.tharaa.mapper.CategoryMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryResponseDto toResponse(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getParentCategory() != null ? category.getParentCategory().getId() : null
        );
    }

    @Override
    public Category toEntity(CategoryRequestDto dto, Category parentCategory) {
        return Category.builder()
                .name(dto.name())
                .parentCategory(parentCategory)
                .build();
    }

    @Override
    public void updateEntityFromRequest(CategoryRequestDto dto, Category category, Category parentCategory) {
        category.setName(dto.name());
        category.setParentCategory(parentCategory);
    }
}
