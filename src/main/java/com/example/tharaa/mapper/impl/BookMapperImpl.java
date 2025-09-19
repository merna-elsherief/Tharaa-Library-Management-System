package com.example.tharaa.mapper.impl;

import com.example.tharaa.domain.entity.Author;
import com.example.tharaa.domain.entity.Book;
import com.example.tharaa.domain.entity.Category;
import com.example.tharaa.domain.entity.Publisher;
import com.example.tharaa.dto.request.BookRequestDto;
import com.example.tharaa.dto.response.BookResponseDto;
import com.example.tharaa.mapper.BookMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookResponseDto toResponse(Book book) {
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPublicationYear(),
                book.getEdition(),
                book.getSummary(),
                book.getLanguage(),
                book.getCoverImageUrl(),
                book.getPublisher() != null ? book.getPublisher().getName() : null,
                book.getCategory() != null ? book.getCategory().getName() : null,
                book.getAuthors().stream().map(Author::getName).collect(Collectors.toSet())
        );
    }

    @Override
    public void updateEntityFromRequest(BookRequestDto dto, Book book, Publisher publisher, Category category, Set<Author> authors) {
        book.setTitle(dto.title());
        book.setIsbn(dto.isbn());
        book.setPublicationYear(dto.publicationYear());
        book.setEdition(dto.edition());
        book.setSummary(dto.summary());
        book.setLanguage(dto.language());
        book.setCoverImageUrl(dto.coverImageUrl());
        book.setPublisher(publisher);
        book.setCategory(category);
        book.setAuthors(authors != null ? authors : new HashSet<>());
    }
}

