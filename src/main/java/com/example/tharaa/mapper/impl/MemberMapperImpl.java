package com.example.tharaa.mapper.impl;

import com.example.tharaa.domain.entity.Member;
import com.example.tharaa.dto.request.MemberRequestDto;
import com.example.tharaa.dto.response.MemberResponseDto;
import com.example.tharaa.mapper.MemberMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberResponseDto toResponse(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getFirstName(),
                member.getLastName(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getAddress()
        );
    }

    @Override
    public Member toEntity(MemberRequestDto dto) {
        return Member.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .address(dto.address())
                .build();
    }

    @Override
    public void updateEntityFromRequest(MemberRequestDto dto, Member member) {
        member.setFirstName(dto.firstName());
        member.setLastName(dto.lastName());
        member.setEmail(dto.email());
        member.setPhoneNumber(dto.phoneNumber());
        member.setAddress(dto.address());
    }
}
