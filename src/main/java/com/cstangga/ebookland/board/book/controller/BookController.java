package com.cstangga.ebookland.board.book.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/board")
public class BookController {
    @GetMapping("/bookboard")
    public void bookboard() {
        log.info("GET /board/bookboard");
    }
}
