package com.cstangga.ebookland.bookboard.controller;


import com.cstangga.ebookland.bookboard.dto.RentalEBookDto;
import com.cstangga.ebookland.bookboard.service.RentalEBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalEBookController {
    private final RentalEBookService rentalEBookService;

    @PostMapping("/rentalEbook")
    @ResponseBody
    public String rentalEbook(@ModelAttribute RentalEBookDto dto)
    {
        log.info("POST /bookboard/rentalEbook");
        log.info("dto = {}",dto);
        rentalEBookService.rentalBook(dto);
        return "success";
    }

    @GetMapping("/readRentalEBook/{bookId}")
    public String readRentalEBook(@PathVariable("bookId")long bookId, Model model)
    {
        log.info("GET /rental/readRentalEBook/{}",bookId);
        RentalEBookDto rentalEBookDto=rentalEBookService.findRentalEbookByBookId(bookId);
        model.addAttribute("dto",rentalEBookDto);
        return "/bookboard/readbook";
    }
}
