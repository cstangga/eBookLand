package com.cstangga.ebookland.board.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/board")
@org.springframework.stereotype.Controller

public class AdminBoardController {
    @GetMapping("/adminboard")
    public void adminboard() {
        log.info("adminboard");
    }

    //게시글 등록

    //게시글 수정

    //게시글 삭제
}
