package com.example.tharaa.mapper.impl;

import com.example.tharaa.domain.entity.Author;
import com.example.tharaa.dto.request.AuthorRequestDto;
import com.example.tharaa.dto.response.AuthorResponseDto;
import com.example.tharaa.mapper.AuthorMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorResponseDto toResponse(Author author) {
        return new AuthorResponseDto(
                author.getId(),
                author.getName()
        );
    }

    @Override
    public Author toEntity(AuthorRequestDto dto) {
        return Author.builder()
                .name(dto.name())
                .build();
    }

    @Override
    public void updateEntityFromRequest(AuthorRequestDto dto, Author author) {
        author.setName(dto.name());
    }
}
