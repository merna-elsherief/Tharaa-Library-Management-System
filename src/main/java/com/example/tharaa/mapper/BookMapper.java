package com.example.tharaa.mapper;

import com.example.tharaa.domain.entity.Author;
import com.example.tharaa.domain.entity.Book;
import com.example.tharaa.domain.entity.Category;
import com.example.tharaa.domain.entity.Publisher;
import com.example.tharaa.dto.request.BookRequestDto;
import com.example.tharaa.dto.response.BookResponseDto;

import java.util.Set;

public interface BookMapper {
    BookResponseDto toResponse(Book book);
    Book toEntity(BookRequestDto dto);
    void updateEntityFromRequest(BookRequestDto dto,
                                 Book book,
                                 Publisher publisher,
                                 Category category,
                                 Set<Author> authors);
}
