package com.example.tharaa.service;

import com.example.tharaa.dto.request.CategoryRequestDto;
import com.example.tharaa.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto getCategoryById(Long id);
    CategoryResponseDto createCategory(CategoryRequestDto dto);
    CategoryResponseDto updateCategory(Long id, CategoryRequestDto dto);
    void deleteCategory(Long id);
}