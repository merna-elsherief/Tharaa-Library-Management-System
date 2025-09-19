package com.example.tharaa.service;

import com.example.tharaa.domain.entity.Author;

import java.util.List;


import com.example.tharaa.dto.request.AuthorRequestDto;
import com.example.tharaa.dto.response.AuthorResponseDto;

import java.util.List;

public interface AuthorService {
    List<AuthorResponseDto> getAllAuthors();
    AuthorResponseDto getAuthorById(Long id);
    AuthorResponseDto createAuthor(AuthorRequestDto dto);
    AuthorResponseDto updateAuthor(Long id, AuthorRequestDto dto);
    void deleteAuthor(Long id);
}
