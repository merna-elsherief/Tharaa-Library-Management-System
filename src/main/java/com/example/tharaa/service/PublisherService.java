package com.example.tharaa.service;

import com.example.tharaa.dto.request.PublisherRequestDto;
import com.example.tharaa.dto.response.PublisherResponseDto;

import java.util.List;

public interface PublisherService {
    List<PublisherResponseDto> getAllPublishers();
    PublisherResponseDto getPublisherById(Long id);
    PublisherResponseDto createPublisher(PublisherRequestDto dto);
    PublisherResponseDto updatePublisher(Long id, PublisherRequestDto dto);
    void deletePublisher(Long id);
}
