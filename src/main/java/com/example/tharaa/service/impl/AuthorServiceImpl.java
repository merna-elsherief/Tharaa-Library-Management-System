package com.example.tharaa.service.impl;

import com.example.tharaa.domain.entity.Author;
import com.example.tharaa.exception.ResourceNotFoundException;
import com.example.tharaa.repository.AuthorRepository;
import com.example.tharaa.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = getAuthorById(id);
        author.setName(authorDetails.getName());
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}
