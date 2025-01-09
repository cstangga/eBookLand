package com.cstangga.ebookland.auth.service;

import com.cstangga.ebookland.auth.principal.AuthPrincipal;
import com.cstangga.ebookland.member.entity.Member;
import com.cstangga.ebookland.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // 얘를 붙히는 순간, Bean으로 등록되기 때문에 WebSecurityCofig에 있는 PasswordEncoder passwordEncoder() 빈 주석처리 -> 안쓰기 때문
@RequiredArgsConstructor
@Slf4j
//
public class AuthService implements UserDetailsService {
    private final MemberRepository memberRepository;

    /**
     * 사용자 아이디로 db에서 사용자 객체를 조회해 UserDetails구현타입(AuthPrincipal)으로 반환한다.
     * - username으로 조회한 결과가 없는 경우 UsernameNotFoundException을 명시적으로 던져줘야 함
     */

    // Member entity는 Member Repository에서 조회합니다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("AuthService / loadUserByUsername");
        log.info("username = {} " , username);
        // orElseThrow() : optional객체를 반환하기 때문에 객체가 있으면 주고, 없으면 예외를 던집니다.
        // UsernameNotFoundException을 던져줘야 spring-security가 인식합니다.
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        log.info("userEmail = {}", member.getEmail());

        // optional객체를 반환하기 때문에 객체가 있으면 주고, 없으면 예외를 던져주세요! 라는 orElseThrow를 사용
        // UsernameNotFoundException을 던져줘야 spring-security가 알아먹음
        return new AuthPrincipal(member); // entity를 UserDetails로 wrapper작업 -> db에서 조회한 것은 entity 때문에 wrapper작업이 필요


    }

}
