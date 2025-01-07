package com.cstangga.ebookland.member.service;

import com.cstangga.ebookland.member.dto.SignupDto;
import com.cstangga.ebookland.member.entity.Member;
import com.cstangga.ebookland.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    public Member memberSave(SignupDto dto) {
        log.info("MemberService / memberSave");
        Member member =new Member().dtoToEntity(dto);
        member.setDefaultAuthorities();
        log.info("등록하는 멤버 = {} ", member);
        return memberRepository.save(member);
    }

    public boolean sameEmailCheck(String email) {
        log.info("MemberService /sameEmailCheck");
        return memberRepository.existsByEmail(email);
    }
}
