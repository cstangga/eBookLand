package com.cstangga.ebookland.board.free.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/board")
@org.springframework.stereotype.Controller
public class FreeBoardController {
    @GetMapping("/freeboard")
    public void   freeBoard() {
        log.info("GET /board/freeboard");
    }

    //게시글 등록

    //게시글 수정

    //게시글 삭제
}
