package com.cstangga.ebookland.member.controller;

import com.cstangga.ebookland.auth.principal.AuthPrincipal;
import com.cstangga.ebookland.bookboard.dto.AllBooksInfoDto;
import com.cstangga.ebookland.bookboard.dto.BuyEBookDto;
import com.cstangga.ebookland.bookboard.dto.BuyPaperBookDto;
import com.cstangga.ebookland.bookboard.dto.RentalBookDto;
import com.cstangga.ebookland.bookboard.service.BookService;
import com.cstangga.ebookland.bookboard.service.EBookService;
import com.cstangga.ebookland.bookboard.service.PaperBookService;
import com.cstangga.ebookland.bookboard.service.RentalEBookService;
import com.cstangga.ebookland.member.dto.MemberDto;
import com.cstangga.ebookland.member.dto.SignupDto;
import com.cstangga.ebookland.member.entity.Member;
import com.cstangga.ebookland.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final RentalEBookService rentalEBookService;
    private final EBookService eBookService;
    private final PaperBookService paperBookService;
    private final BookService bookService;

    @GetMapping("/signup")
    public void signup(){
        log.info("GET /member/signup");
    }

    @PostMapping("/sameEmailCheck")
    @ResponseBody
    public boolean sameEmailCheck(@RequestParam("email") String email)
    {
        log.info("POST /member/sameEmailCheck");
        log.info("이메일 : {}", email);
        return memberService.sameEmailCheck(email);
    }

    @PostMapping("/registermember")
    private String registermember(@ModelAttribute SignupDto dto)
    {
        log.info("POST /member/registerMember");
        Member member=memberService.memberSave(dto);
        log.debug("member = {}", member);
        return "redirect:/";
    }


    @GetMapping("/mypage")
    public void mypage(@AuthenticationPrincipal AuthPrincipal authPrincipal, Model model) {
        log.info("GET /member/myPage");
        log.info("principal = {}", authPrincipal.getUsername());
        MemberDto dto=memberService.findMemberByEmail(authPrincipal.getUsername());
        log.info("dto = {}", dto);
        model.addAttribute("memberDto",dto);

        List<AllBooksInfoDto> allBooksInfoDtoList=bookService.findAllBook(dto.getMemberId());

        model.addAttribute("allBuyInfoDtoList",allBooksInfoDtoList);

    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberDto dto,Model model)
    {
        log.info("POST /member/update");
        log.info("dto = {}", dto);
        dto=memberService.memberUpdate(dto);
        model.addAttribute("memberDto", dto);
        return "redirect:/member/mypage";
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public String  updatePassword(@RequestParam("newPassword") String password,@RequestParam("memberId")String memberId)
    {
        log.info("POST /member/updatePassword");
        log.info("password = {}", password);
        log.info("memberId = {}", memberId);
        memberService.updatePassword(memberId,password);
        return "success";
    }

    @PostMapping("/passwordCheck")
    @ResponseBody
    public boolean passwordCheck(@RequestParam("password") String password,@RequestParam("memberId")String memberId)
    {
        log.info("GET /member/passwordCheck");
        log.info("password : {}", password);
        log.info("memberId : {}", memberId);
        return memberService.checkPassword(memberId,password);
    }



}
