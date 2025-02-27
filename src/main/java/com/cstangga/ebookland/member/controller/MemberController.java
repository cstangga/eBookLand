package com.cstangga.ebookland.member.controller;

import com.cstangga.ebookland.auth.principal.AuthPrincipal;
import com.cstangga.ebookland.bookboard.dto.*;
import com.cstangga.ebookland.bookboard.entity.BuyPaperBook;
import com.cstangga.ebookland.bookboard.repository.BuyEbookRepository;
import com.cstangga.ebookland.bookboard.repository.BuyPaperBookRepository;
import com.cstangga.ebookland.bookboard.service.BookService;
import com.cstangga.ebookland.bookboard.service.EBookService;
import com.cstangga.ebookland.bookboard.service.PaperBookService;
import com.cstangga.ebookland.bookboard.service.RentalEBookService;
import com.cstangga.ebookland.chat.entity.ChatRoom;
import com.cstangga.ebookland.chat.service.ChatService;
import com.cstangga.ebookland.member.dto.MemberDto;
import com.cstangga.ebookland.member.dto.SignupDto;
import com.cstangga.ebookland.member.entity.Member;
import com.cstangga.ebookland.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.MetaMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final RentalEBookService rentalEBookService;
    private final EBookService eBookService;
    private final BookService bookService;
    private final ChatService chatService;
    private final ConversionService conversionService;


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
        ChatRoom entity=chatService.createRoom(member);
        log.info("memberEntity = {}", member);
        log.info("chatRoomEntity = {}", entity);

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

    @GetMapping("/mybook")
    public void myBook(@AuthenticationPrincipal AuthPrincipal authPrincipal, Model model) {
        log.info("GET /bookboard/myBook");
        log.info("principal = {}", authPrincipal.getUsername());

        long memberId=authPrincipal.getMember().getId();
        List<RentalEBookDto> rentalEBookDtoList = rentalEBookService.findAllRentalEbookById(memberId);
        List<BuyEBookDto> buyEBookDtoList = eBookService.findAllEBookById(memberId);

        model.addAttribute("rentalEBookDtoList",rentalEBookDtoList);
        model.addAttribute("buyEBookDtoList",buyEBookDtoList);
    }

    @GetMapping("/api/member/roomId")
    @ResponseBody  // ✅ This makes the method return raw data (not an HTML view)
    public ResponseEntity<?> getUserRoomId(@AuthenticationPrincipal AuthPrincipal member) {
        log.info("GET /api/member/roomId");
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        Map<String, Object> memberInfo = new HashMap<>();
        memberInfo.put("roomId", member.getMember().getRoomId());
        memberInfo.put("nickName", member.getMember().getNickName());
        log.info("memberInfo = {}", memberInfo);
        return ResponseEntity.ok(memberInfo);
    }

}
