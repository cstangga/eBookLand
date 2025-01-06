package com.cstangga.ebookland.board.notice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/board")
public class NoticeBoardController {

    @GetMapping("/noticeboard")
    public void noticeboard() {
        log.info("GET /board/noticeboard");
    }
}
