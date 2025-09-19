package com.example.tharaa.service.impl;

import com.example.tharaa.domain.entity.Member;
import com.example.tharaa.dto.request.MemberRequestDto;
import com.example.tharaa.dto.response.MemberResponseDto;
import com.example.tharaa.exception.ResourceNotFoundException;
import com.example.tharaa.mapper.MemberMapper;
import com.example.tharaa.repository.MemberRepository;
import com.example.tharaa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public List<MemberResponseDto> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(memberMapper::toResponse)
                .toList();
    }

    @Override
    public MemberResponseDto getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
        return memberMapper.toResponse(member);
    }

    @Override
    public MemberResponseDto createMember(MemberRequestDto dto) {
        Member member = memberMapper.toEntity(dto);
        return memberMapper.toResponse(memberRepository.save(member));
    }

    @Override
    public MemberResponseDto updateMember(Long id, MemberRequestDto dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
        memberMapper.updateEntityFromRequest(dto, member);
        return memberMapper.toResponse(memberRepository.save(member));
    }

    @Override
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }
}