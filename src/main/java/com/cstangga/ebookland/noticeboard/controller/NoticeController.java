package com.cstangga.ebookland.noticeboard.controller;

import com.cstangga.ebookland.auth.principal.AuthPrincipal;
import com.cstangga.ebookland.noticeboard.dto.NoticeDto;
import com.cstangga.ebookland.noticeboard.entity.Notice;
import com.cstangga.ebookland.noticeboard.repository.NoticeRepository;
import com.cstangga.ebookland.noticeboard.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/noticeboard")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;
    private final NoticeRepository noticeRepository;


    @GetMapping("/list")
    public void noticeboard(Model model) {
        log.info("GET /noticeboard/list");
        List<NoticeDto> noticeDtoList=noticeService.findAll();
        model.addAttribute("noticeDtoList", noticeDtoList);

    }

    @PostMapping("/addNotice")
    public String  addNotice(NoticeDto noticeDto){
        log.info("POST / noticeboard / addNotice()");
        Notice entity=noticeService.addNotice(noticeDto);
        log.info("noticeEntity = {}",entity);
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
        // 여기서 조회수가 올라간다.
        model.addAttribute("dto",dto);
        return "/noticeboard/detail";

    }

        @PostMapping("/update")
        public String update(NoticeDto dto, Model model){
            log.info("POST / noticeboard / update / {}",dto.getNoticeId());
            log.info("noticeEntity = {}",noticeService.update(dto));
            return "redirect:/noticeboard/list";

        }

    @GetMapping("/modify/{noticeId}")
    public String  modify(@PathVariable("noticeId")long noticeId,Model model)
    {
        log.info("GET / noticeboard / modify / {}",noticeId);

        NoticeDto dto=noticeService.findNoticeById(noticeId);
        model.addAttribute("dto",dto);
        return "/noticeboard/modify";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("noticeId")long noticeId){
        log.info("GET / noticeboard / delete / {}",noticeId);
        noticeService.delete(noticeId);
        return "redirect:/noticeboard/list";

    }

    @GetMapping("/debug")
    public String debug(@AuthenticationPrincipal Object principal) {
        System.out.println("🔥 principal class: " + principal.getClass().getName());
        return "index"; // 아무 페이지나 return 해도 OK (템플릿 이름)
    }

    /*
    * true = 추천이 됨
    * false = 추천 안됨(중복방지) -> 이미 하셧습니다 추천을 취소 하시겠습니까??
    * Entity를 다시 생각해 봐야겟다
    * */
    @PostMapping("/disLikes")
    public long disLikes(@RequestParam("user")String user, @RequestParam("noticeId") long noticeId){
        log.info("POST / noticeboard / dislikes");
        log.info("user = {}, noticeId = {}",user,noticeId);
        noticeService.recommendCheck("disLikes",user,noticeId);
        return 1;
    }

    @PostMapping("/likes")
    public long likes(@RequestParam("user")String user, @RequestParam("noticeId") long noticeId){
        log.info("POST / noticeboard / likes");
        log.info("user = {}, noticeId = {}",user,noticeId);
        return 1;
    }
}
