package com.example.tharaa.mapper;

import com.example.tharaa.domain.entity.Member;
import com.example.tharaa.dto.request.MemberRequestDto;
import com.example.tharaa.dto.response.MemberResponseDto;

public interface MemberMapper {
    MemberResponseDto toResponse(Member member);
    Member toEntity(MemberRequestDto dto);
    void updateEntityFromRequest(MemberRequestDto dto, Member member);
}