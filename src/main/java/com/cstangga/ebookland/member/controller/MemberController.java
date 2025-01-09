package com.cstangga.ebookland.member.controller;

import com.cstangga.ebookland.member.dto.SignupDto;
import com.cstangga.ebookland.member.entity.Member;
import com.cstangga.ebookland.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public void signup(){
        log.info("GET /member/signup");
    }

    @PostMapping("/sameEmailCheck")
    @ResponseBody
    public boolean sameEmailCheck(@RequestParam("email") String email)
    {
        log.info("GET /member/sameEmailCheck");
        log.info("이메일 : {}", email);
        return memberService.sameEmailCheck(email);
    }

    @PostMapping("/resigstmember")
    private String registMember(@ModelAttribute SignupDto dto)
    {
        log.info("POST /member/resigstMember");

        try {
            String encodedPassword = passwordEncoder.encode(dto.getPassword());
            log.info("password : {}", encodedPassword);
            dto.setPassword(encodedPassword); // 암호와
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("dto = " + dto);

        Member member=memberService.memberSave(dto);
        log.debug("member = {}", member);
        return "redirect:/";
    }

}
