package com.example.tharaa.mapper;

import com.example.tharaa.domain.entity.Publisher;
import com.example.tharaa.dto.request.PublisherRequestDto;
import com.example.tharaa.dto.response.PublisherResponseDto;

public interface PublisherMapper {
    PublisherResponseDto toResponse(Publisher publisher);
    Publisher toEntity(PublisherRequestDto dto);
    void updateEntityFromRequest(PublisherRequestDto dto, Publisher publisher);
}
