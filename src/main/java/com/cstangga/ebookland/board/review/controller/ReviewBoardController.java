package com.cstangga.ebookland.board.review.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/board")
public class ReviewBoardController {

    @GetMapping("/reviewboard")
    public void reviewboard() {
        log.info("reviewBoard");
    }
}
