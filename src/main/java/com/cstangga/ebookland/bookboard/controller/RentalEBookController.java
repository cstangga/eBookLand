package com.cstangga.ebookland.bookboard.controller;


import com.cstangga.ebookland.bookboard.dto.RentalEBookDto;
import com.cstangga.ebookland.bookboard.service.RentalEBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
