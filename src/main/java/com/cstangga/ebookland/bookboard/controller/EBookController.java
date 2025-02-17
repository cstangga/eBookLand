package com.cstangga.ebookland.bookboard.controller;

import com.cstangga.ebookland.bookboard.dto.BuyEBookDto;
import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.BuyEbook;
import com.cstangga.ebookland.bookboard.service.BookService;
import com.cstangga.ebookland.bookboard.service.EBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/ebook")
@RequiredArgsConstructor
public class EBookController {
    private final EBookService ebookService;
    private final BookService bookService;

    @PostMapping("/buyEBook")
    public String buyBook(@ModelAttribute BuyEBookDto dto)
    {
        log.info("POST /ebook/buyBook");
        log.info("dto = {}",dto);
        BuyEbook buyEbookEntity=ebookService.buyEbook(dto);
        log.info("buyEbookEntity = {}",buyEbookEntity);
        return String.format("redirect:/bookboard/detail/%d",dto.getBookId());
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
