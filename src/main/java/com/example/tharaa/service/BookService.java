package com.example.tharaa.service;

import com.example.tharaa.dto.request.BookRequestDto;
import com.example.tharaa.dto.response.BookResponseDto;

import java.util.List;

public interface BookService {
    List<BookResponseDto> getAllBooks();
    BookResponseDto getBookById(Long id);
    BookResponseDto createBook(BookRequestDto dto);
    BookResponseDto updateBook(Long id, BookRequestDto dto);
    void deleteBook(Long id);
}

