package com.cstangga.ebookland.member.service;

import com.cstangga.ebookland.member.dto.MemberDto;
import com.cstangga.ebookland.member.dto.SignupDto;
import com.cstangga.ebookland.member.entity.Member;
import com.cstangga.ebookland.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member memberSave(SignupDto dto) {
        log.info("MemberService / memberSave");
        try {
            String encodedPassword = passwordEncoder.encode(dto.getPassword());
            log.info("password : {}", encodedPassword);
            dto.setPassword(encodedPassword); // 암호와
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Member member =dto.toEntity();
        log.info("member = {}", member);
        member.setDefaultAuthorities();

        log.info("등록하는 멤버 = {} ", member);
        return memberRepository.save(member);
    }

    public MemberDto memberUpdate(MemberDto dto) {
        log.info("MemberService / memberUpdate");
        log.info("dto = {}", dto);
        Member member=memberRepository.findMemberById(dto.getMemberId());
        log.info("member = {}", member);
        member.update(dto);
        memberRepository.save(member);
        return dto;
    }

    public boolean sameEmailCheck(String email) {
        log.info("MemberService /sameEmailCheck");
        return memberRepository.existsByEmail(email);
    }

    public MemberDto findMemberByEmail(String email) {
        log.info("MemberService /findMemberByEmail");
        Member member = memberRepository.findMemberByEmail(email);
        MemberDto memberDto = new MemberDto();
        memberDto=memberDto.dtoToEntity();
        log.info("Member = {}", member);
        log.info("MemberDto = {}", memberDto);
        return memberDto;

    }

    public boolean checkPassword(String memberId,String password) {
        log.info("MemberService /findMemberByPassword");
        Member member=memberRepository.findMemberById(Long.parseLong(memberId));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        log.info("비밀번호 매치여부 {}", encoder.matches(password, member.getPassword()));
        return encoder.matches(password, member.getPassword());
    }


    public void updatePassword(String memberId, String password) {
        Member member=memberRepository.findMemberById(Long.parseLong(memberId));
        String newPassword=passwordEncoder.encode(password);
        member.setPassword(newPassword);
        memberRepository.save(member);
    }
}
