package com.example.tharaa.mapper;

import com.example.tharaa.domain.entity.Author;
import com.example.tharaa.dto.request.AuthorRequestDto;
import com.example.tharaa.dto.response.AuthorResponseDto;

public interface AuthorMapper {
    AuthorResponseDto toResponse(Author author);
    Author toEntity(AuthorRequestDto dto);
    void updateEntityFromRequest(AuthorRequestDto dto, Author author);
}
