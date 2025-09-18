package com.example.tharaa.service.impl;


import com.example.tharaa.domain.entity.Book;
import com.example.tharaa.repository.BookRepository;
import com.example.tharaa.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        book.setTitle(bookDetails.getTitle());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublicationYear(bookDetails.getPublicationYear());
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
