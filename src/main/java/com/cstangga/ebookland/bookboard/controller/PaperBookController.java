package com.cstangga.ebookland.bookboard.controller;


import com.cstangga.ebookland.bookboard.dto.BuyEBookDto;
import com.cstangga.ebookland.bookboard.dto.BuyPaperBookDto;
import com.cstangga.ebookland.bookboard.entity.BuyPaperBook;
import com.cstangga.ebookland.bookboard.service.PaperBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/paperbook")
@RequiredArgsConstructor
public class PaperBookController {
    private final PaperBookService paperBookService;

    @PostMapping("/buyPaperBook")
    public String buyBook(@ModelAttribute BuyPaperBookDto dto)
    {
        log.info("POST /paperbook/buyPaperBook");
        log.info("dto = {}",dto);
        boolean status=paperBookService.buyPaperBook(dto);
        log.info("buy_status = {}",status);

        return String.format("redirect:/bookboard/detail/%d",dto.getBookId());
    }
}
