package com.cstangga.ebookland.bookboard.controller;

import com.cstangga.ebookland.bookboard.dto.BuyEBookDto;
import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.BuyEbook;
import com.cstangga.ebookland.bookboard.service.BookService;
import com.cstangga.ebookland.bookboard.service.EBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/ebook")
@RequiredArgsConstructor
public class EBookController {
    private final EBookService ebookService;
    private final BookService bookService;

    @PostMapping("/buyEBook")
    public ResponseEntity<?> buyBook(@ModelAttribute BuyEBookDto dto)
    {
        log.info("POST /ebook/buyBook");
        log.info("dto = {}",dto);
        if(ebookService.duplicatedCheck(dto)) {
            BuyEbook buyEbookEntity = ebookService.buyEbook(dto);
            log.info("buyEbookEntity = {}",buyEbookEntity);

            return ResponseEntity.ok(Map.of("success", true, "message", "전자책 구매 완료"));
        }
        else {
            return ResponseEntity.ok(Map.of("success", false, "message", "이미 전자책 구매를 했습니다."));
        }
    }

    @GetMapping("/readBuyEBook/{bookId}")
    public String readBook(@PathVariable("bookId")long bookId, Model model)
    {
        log.info("GET /ebook/readBuyEBook/{}",bookId);
        BuyEBookDto eBookDto=ebookService.findEBookByBookId(bookId);
        model.addAttribute("dto",eBookDto);
        return "/bookboard/readbook";
    }
}
