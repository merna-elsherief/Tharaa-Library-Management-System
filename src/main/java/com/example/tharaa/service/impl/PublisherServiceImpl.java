package com.example.tharaa.service.impl;

import com.example.tharaa.domain.entity.Publisher;
import com.example.tharaa.dto.request.PublisherRequestDto;
import com.example.tharaa.dto.response.PublisherResponseDto;
import com.example.tharaa.exception.ResourceNotFoundException;
import com.example.tharaa.mapper.PublisherMapper;
import com.example.tharaa.repository.PublisherRepository;
import com.example.tharaa.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @Override
    public List<PublisherResponseDto> getAllPublishers() {
        return publisherRepository.findAll()
                .stream()
                .map(publisherMapper::toResponse)
                .toList();
    }

    @Override
    public PublisherResponseDto getPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + id));
        return publisherMapper.toResponse(publisher);
    }

    @Override
    public PublisherResponseDto createPublisher(PublisherRequestDto dto) {
        Publisher publisher = publisherMapper.toEntity(dto);
        return publisherMapper.toResponse(publisherRepository.save(publisher));
    }

    @Override
    public PublisherResponseDto updatePublisher(Long id, PublisherRequestDto dto) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + id));
        publisherMapper.updateEntityFromRequest(dto, publisher);
        return publisherMapper.toResponse(publisherRepository.save(publisher));
    }

    @Override
    public void deletePublisher(Long id) {
        if (!publisherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Publisher not found with id: " + id);
        }
        publisherRepository.deleteById(id);
    }
}