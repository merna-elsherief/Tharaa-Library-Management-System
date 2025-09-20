package com.example.tharaa.dto.response;

import com.example.tharaa.dto.CategoryDTO;

import java.util.Set;

public record BookResponseDto(
        Long id,
        String title,
        String isbn,
        int publicationYear,
        String edition,
        String summary,
        String language,
        String coverImageUrl,
        String publisherName,
        CategoryResponseDto category,
        Set<String> authorNames
) {}
