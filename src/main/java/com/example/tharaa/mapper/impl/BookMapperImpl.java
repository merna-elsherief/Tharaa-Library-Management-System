package com.example.tharaa.mapper.impl;

import com.example.tharaa.domain.entity.Author;
import com.example.tharaa.domain.entity.Book;
import com.example.tharaa.domain.entity.Category;
import com.example.tharaa.domain.entity.Publisher;
import com.example.tharaa.dto.request.BookRequestDto;
import com.example.tharaa.dto.response.BookResponseDto;
import com.example.tharaa.dto.response.CategoryParentDto;
import com.example.tharaa.dto.response.CategoryResponseDto;
import com.example.tharaa.mapper.BookMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookMapperImpl implements BookMapper {

    private CategoryResponseDto toCategoryResponse(Category category) {
        if (category == null) return null;

        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getParentCategory() != null
                        ? new CategoryParentDto(
                        category.getParentCategory().getId(),
                        category.getParentCategory().getName()
                )
                        : null,
                category.getSubCategories() != null
                        ? category.getSubCategories()
                        .stream()
                        .map(this::toCategoryResponse)
                        .collect(Collectors.toSet())
                        : Set.of()
        );
    }


    @Override
    public BookResponseDto toResponse(Book book) {
        String publisherName = book.getPublisher() != null ? book.getPublisher().getName() : null;
        CategoryResponseDto categoryDto = toCategoryResponse(book.getCategory());

        Set<String> authorNames = new HashSet<>();
        if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
            authorNames = book.getAuthors()
                    .stream()
                    .map(Author::getName)
                    .collect(Collectors.toSet());
        }

        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPublicationYear(),
                book.getEdition(),
                book.getSummary(),
                book.getLanguage(),
                book.getCoverImageUrl() != null ? book.getCoverImageUrl() : "", // safe
                publisherName,
                categoryDto,
                authorNames
        );
    }

    @Override
    public Book toEntity(BookRequestDto dto) {
        return Book.builder()
                .title(dto.title())
                .isbn(dto.isbn())
                .publicationYear(dto.publicationYear())
                .edition(dto.edition())
                .summary(dto.summary())
                .language(dto.language())
                .coverImageUrl(dto.coverImageUrl())
                .build();
    }

    @Override
    public void updateEntityFromRequest(BookRequestDto dto,
                                        Book book,
                                        Publisher publisher,
                                        Category category,
                                        Set<Author> authors) {
        book.setTitle(dto.title());
        book.setIsbn(dto.isbn());
        book.setPublicationYear(dto.publicationYear());
        book.setEdition(dto.edition());
        book.setSummary(dto.summary());
        book.setLanguage(dto.language());
        book.setCoverImageUrl(dto.coverImageUrl());
        book.setPublisher(publisher);
        book.setCategory(category);
        book.setAuthors(authors);
    }
}
