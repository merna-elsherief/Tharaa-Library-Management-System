package com.example.tharaa.service;

import com.example.tharaa.domain.entity.Member;

import java.util.List;

public interface MemberService {
    List<Member> getAllMembers();
    Member getMemberById(Long id);
    Member createMember(Member member);
    Member updateMember(Long id, Member memberDetails);
    void deleteMember(Long id);
}
