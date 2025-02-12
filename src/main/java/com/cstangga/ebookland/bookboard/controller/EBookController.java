package com.cstangga.ebookland.bookboard.controller;

import com.cstangga.ebookland.bookboard.dto.BuyEBookDto;
import com.cstangga.ebookland.bookboard.entity.BuyEbook;
import com.cstangga.ebookland.bookboard.service.EBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/ebook")
@RequiredArgsConstructor
public class EBookController {
    private final EBookService ebookService;

    @PostMapping("/buyEBook")
    public String buyBook(@ModelAttribute BuyEBookDto dto)
    {
        log.info("POST /ebook/buyBook");
        log.info("dto = {}",dto);
        BuyEbook buyEbookEntity=ebookService.buyEbook(dto);
        log.info("buyEbookEntity = {}",buyEbookEntity);
        return String.format("redirect:/bookboard/detail/%d",dto.getBookId());
    }
}
