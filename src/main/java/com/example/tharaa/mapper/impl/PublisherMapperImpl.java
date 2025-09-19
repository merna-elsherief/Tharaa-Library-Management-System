package com.example.tharaa.mapper.impl;

import com.example.tharaa.domain.entity.Publisher;
import com.example.tharaa.dto.request.PublisherRequestDto;
import com.example.tharaa.dto.response.PublisherResponseDto;
import com.example.tharaa.mapper.PublisherMapper;
import org.springframework.stereotype.Component;

@Component
public class PublisherMapperImpl implements PublisherMapper {

    @Override
    public PublisherResponseDto toResponse(Publisher publisher) {
        return new PublisherResponseDto(
                publisher.getId(),
                publisher.getName(),
                publisher.getAddress(),
                publisher.getWebsite()
        );
    }

    @Override
    public Publisher toEntity(PublisherRequestDto dto) {
        return Publisher.builder()
                .name(dto.name())
                .address(dto.address())
                .website(dto.website())
                .build();
    }

    @Override
    public void updateEntityFromRequest(PublisherRequestDto dto, Publisher publisher) {
        publisher.setName(dto.name());
        publisher.setAddress(dto.address());
        publisher.setWebsite(dto.website());
    }
}
