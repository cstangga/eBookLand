package com.cstangga.ebookland.noticeboard.controller;

import com.cstangga.ebookland.auth.principal.AuthPrincipal;
import com.cstangga.ebookland.noticeboard.dto.NoticeDto;
import com.cstangga.ebookland.noticeboard.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/noticeboard")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;


    @GetMapping("/list")
    public void noticeboard(Model model) {
        log.info("GET /noticeboard/list");
        List<NoticeDto> noticeDtoList=noticeService.findAll();
        model.addAttribute("noticeDtoList", noticeDtoList);

    }

    @PostMapping("/addNotice")
    public String  addNotice(NoticeDto noticeDto){
        log.info("POST / noticeboard / addNotice()");
        noticeService.addNotice(noticeDto);
        return "redirect:/noticeboard/list";
    }

    //게시글 등록 페이지
    @GetMapping("/addnotice")
    public void addPost(@AuthenticationPrincipal AuthPrincipal authPrincipal, Model model)
    {
        log.info("GET /noticeboard / addNotice");
        model.addAttribute("memberId", authPrincipal.getMember().getId());
    }

    @GetMapping("/detail/{noticeId}")
    public String detail(@PathVariable("noticeId")long noticeId, Model model){
        log.info("GET / noticeboard / detail / {}",noticeId);
        NoticeDto dto=noticeService.findNoticeById(noticeId);
        model.addAttribute("dto",dto);
        return "/noticeboard/detail";

    }
}
