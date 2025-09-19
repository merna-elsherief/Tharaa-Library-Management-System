package com.example.tharaa.mapper;

import com.example.tharaa.domain.entity.Category;
import com.example.tharaa.dto.request.CategoryRequestDto;
import com.example.tharaa.dto.response.CategoryResponseDto;

public interface CategoryMapper {
    CategoryResponseDto toResponse(Category category);
    Category toEntity(CategoryRequestDto dto, Category parentCategory);
    void updateEntityFromRequest(CategoryRequestDto dto, Category category, Category parentCategory);
}
