package com.example.tharaa.dto.request;

import java.util.Set;

public record BookRequestDto(
        String title,
        String isbn,
        int publicationYear,
        String edition,
        String summary,
        String language,
        String coverImageUrl,
        Long publisherId,
        Long categoryId,
        Set<Long> authorIds
) {}
