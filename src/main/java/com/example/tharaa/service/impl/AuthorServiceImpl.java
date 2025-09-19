package com.example.tharaa.service.impl;

import com.example.tharaa.domain.entity.Author;
import com.example.tharaa.dto.request.AuthorRequestDto;
import com.example.tharaa.dto.response.AuthorResponseDto;
import com.example.tharaa.exception.ResourceNotFoundException;
import com.example.tharaa.mapper.AuthorMapper;
import com.example.tharaa.repository.AuthorRepository;
import com.example.tharaa.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorResponseDto> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toResponse)
                .toList();
    }

    @Override
    public AuthorResponseDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
        return authorMapper.toResponse(author);
    }

    @Override
    public AuthorResponseDto createAuthor(AuthorRequestDto dto) {
        Author author = authorMapper.toEntity(dto);
        return authorMapper.toResponse(authorRepository.save(author));
    }

    @Override
    public AuthorResponseDto updateAuthor(Long id, AuthorRequestDto dto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
        authorMapper.updateEntityFromRequest(dto, author);
        return authorMapper.toResponse(authorRepository.save(author));
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}
