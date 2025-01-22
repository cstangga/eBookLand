package com.cstangga.ebookland.noticeboard;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/noticeboard")
public class NoticeBoardController {

    @GetMapping("/list")
    public void noticeboard() {
        log.info("GET /noticeboard/list");
    }
}
