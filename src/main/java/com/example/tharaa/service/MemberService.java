package com.example.tharaa.service;

import com.example.tharaa.dto.request.MemberRequestDto;
import com.example.tharaa.dto.response.MemberResponseDto;

import java.util.List;

public interface MemberService {
    List<MemberResponseDto> getAllMembers();
    MemberResponseDto getMemberById(Long id);
    MemberResponseDto createMember(MemberRequestDto dto);
    MemberResponseDto updateMember(Long id, MemberRequestDto dto);
    void deleteMember(Long id);
}