package com.cstangga.ebookland.book.controller;

import com.cstangga.ebookland.book.dto.BookRegistDto;
import com.cstangga.ebookland.book.service.BookService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;


    @GetMapping("/registbook")
    private void registbook(){
        log.info("GET /book/registbook");
    }
    @PostMapping("/registbook")
    private void registbook(@ModelAttribute BookRegistDto dto, @RequestParam("bookImage")MultipartFile bookImage) throws IOException {
        log.info("POST /book/registbook");
        log.info("dto = {}",dto);
        log.info("bookImage = {}",bookImage.getName());

        dto.setUploadPath(bookService.saveImage(bookImage));
        bookService.save(dto);

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
