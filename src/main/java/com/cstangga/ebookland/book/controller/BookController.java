package com.cstangga.ebookland.book.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {

    @GetMapping("/registbook")
    private void registbook(){
        log.info("GET /book/registbook");
    }

    @GetMapping("/updatebook")
    private void updatebook(){
        log.info("GET /book/updatebook");
    }

    @GetMapping("/rental")
    private void rental(){
        log.info("GET /book/rental");
    }

    @GetMapping("/purchase")
    private void purchase(){
        log.info("GET /book/purchase");
    }
}
