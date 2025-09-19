package com.example.tharaa.service.impl;

import com.example.tharaa.domain.entity.Category;
import com.example.tharaa.dto.request.CategoryRequestDto;
import com.example.tharaa.dto.response.CategoryResponseDto;
import com.example.tharaa.exception.ResourceNotFoundException;
import com.example.tharaa.mapper.CategoryMapper;
import com.example.tharaa.repository.CategoryRepository;
import com.example.tharaa.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto dto) {
        Category parent = null;
        if (dto.parentCategoryId() != null) {
            parent = categoryRepository.findById(dto.parentCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found with id: " + dto.parentCategoryId()));
        }
        Category category = categoryMapper.toEntity(dto, parent);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        Category parent = null;
        if (dto.parentCategoryId() != null) {
            parent = categoryRepository.findById(dto.parentCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found with id: " + dto.parentCategoryId()));
        }

        categoryMapper.updateEntityFromRequest(dto, category, parent);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}