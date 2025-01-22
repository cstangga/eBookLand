package com.cstangga.ebookland.freeboard;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@RequestMapping("/freeboard")
@Controller
public class FreeBoardController {
    @GetMapping("/list")
    public void list() {
        log.info("GET /freeboard/list");
    }

    //게시글 등록

    //게시글 수정

    //게시글 삭제
}
