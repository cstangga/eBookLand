package com.cstangga.ebookland.bookboard.controller;


import com.cstangga.ebookland.bookboard.dto.RentalEBookDto;
import com.cstangga.ebookland.bookboard.service.RentalEBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalEBookController {
    private final RentalEBookService rentalEBookService;

    @PostMapping("/rentalEbook")
    @ResponseBody
    public ResponseEntity<?> rentalEbook(@ModelAttribute RentalEBookDto dto)
    {
        log.info("POST /bookboard/rentalEbook");
        log.info("dto = {}",dto);
        if(rentalEBookService.duplicatedCheck(dto)) {
            rentalEBookService.rentalBook(dto);
            return ResponseEntity.ok(Map.of("success", true, "message", "전자책 대여 완료"));

        }else{
            return ResponseEntity.ok(Map.of("success", false, "message", "이미 전자책 대여를 했습니다."));

        }
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
