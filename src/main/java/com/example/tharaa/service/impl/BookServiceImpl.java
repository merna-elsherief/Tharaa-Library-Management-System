package com.example.tharaa.service.impl;

import com.example.tharaa.domain.entity.Author;
import com.example.tharaa.domain.entity.Book;
import com.example.tharaa.domain.entity.Category;
import com.example.tharaa.domain.entity.Publisher;
import com.example.tharaa.dto.request.BookRequestDto;
import com.example.tharaa.dto.response.BookResponseDto;
import com.example.tharaa.exception.ResourceNotFoundException;
import com.example.tharaa.mapper.BookMapper;
import com.example.tharaa.repository.AuthorRepository;
import com.example.tharaa.repository.BookRepository;
import com.example.tharaa.repository.CategoryRepository;
import com.example.tharaa.repository.PublisherRepository;
import com.example.tharaa.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    @Override
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return bookMapper.toResponse(book);
    }

    @Override
    public BookResponseDto createBook(BookRequestDto dto) {
        Publisher publisher = getPublisher(dto.publisherId());
        Category category = getCategory(dto.categoryId());
        Set<Author> authors = getAuthors(dto.authorIds());

        Book book = new Book();
        bookMapper.updateEntityFromRequest(dto, book, publisher, category, authors);
        return bookMapper.toResponse(bookRepository.save(book));
    }

    @Override
    public BookResponseDto updateBook(Long id, BookRequestDto dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        Publisher publisher = getPublisher(dto.publisherId());
        Category category = getCategory(dto.categoryId());
        Set<Author> authors = getAuthors(dto.authorIds());

        bookMapper.updateEntityFromRequest(dto, book, publisher, category, authors);
        return bookMapper.toResponse(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    // Helpers
    private Publisher getPublisher(Long id) {
        if (id == null) return null;
        return publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + id));
    }

    private Category getCategory(Long id) {
        if (id == null) return null;
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    private Set<Author> getAuthors(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) return new HashSet<>();
        Set<Author> authors = new HashSet<>(authorRepository.findAllById(ids));
        if (authors.isEmpty()) {
            throw new ResourceNotFoundException("No authors found with given IDs");
        }
        return authors;
    }
}
