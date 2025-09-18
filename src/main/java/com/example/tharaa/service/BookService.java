package com.example.tharaa.service;

import com.example.tharaa.domain.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book createBook(Book book);
    Book updateBook(Long id, Book bookDetails);
    void deleteBook(Long id);
}

