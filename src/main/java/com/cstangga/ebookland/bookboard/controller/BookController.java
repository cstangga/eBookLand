package com.cstangga.ebookland.bookboard.controller;

import com.cstangga.ebookland.auth.principal.AuthPrincipal;
import com.cstangga.ebookland.bookboard.dto.BookListDto;
import com.cstangga.ebookland.bookboard.dto.BookModifyDto;
import com.cstangga.ebookland.bookboard.dto.BookPurchaseRequestDto;
import com.cstangga.ebookland.bookboard.dto.BookRegistDto;
import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.service.BookService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/bookboard")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/list")
    public void list(Model model) {
        List<Book> books = bookService.findAll();
        List<BookListDto> bookDtoList = new ArrayList<>();
        for (Book book : books) {
            bookDtoList.add(new BookListDto().entityToDto(book));
        }
        model.addAttribute("bookDtoList", bookDtoList);
    }

    @GetMapping("/registbook")
    private void registbook(){
        log.info("GET /bookboard/registbook");
    }

    @PostMapping("/registbook")
    private void registbook(@ModelAttribute BookRegistDto dto, @RequestParam("bookImage")MultipartFile bookImage) throws IOException {
        log.info("POST /bookboard/registbook");
        log.info("dto = {}",dto);
        log.info("bookImage = {}",bookImage.getName());

        dto.setImagePath(bookService.saveImage(bookImage));
        Book entity=dto.dtoToEntity();
        bookService.save(entity);

    }

    @GetMapping("/modifybook/{bookId}") // 책 정보를 바꿀때 detail
    public String modifybook(@PathVariable("bookId") long bookId, Model model)
    {
        log.info("GET /bookboard/modifybook/{}",bookId);
        BookModifyDto bookDto=new BookModifyDto().entityToDto(bookService.findByBookId(bookId));
        log.info("bookDto = {}",bookDto);
        model.addAttribute("bookDto",bookDto);
        return "bookboard/modifybook";
    }

    @GetMapping("/detail/{bookId}") // 사용자가 보는 detail
    public String detail(@PathVariable("bookId") long bookId, Model model)
    {
        log.info("GET /bookboard/detail/{}",bookId);
        BookModifyDto bookDto=new BookModifyDto().entityToDto(bookService.findByBookId(bookId));
        log.info("bookDto = {}",bookDto);
        model.addAttribute("bookDto",bookDto);
        return "bookboard/detail";
    }

    @PostMapping("/update") // 도서 상태 업데이트
    public String modify(BookModifyDto dto, Model model,@RequestParam("bookImage")MultipartFile bookImage) throws IOException
    {
        log.info("POST /bookboard/modify");
        log.info("dto = {}",dto);
        log.info("bookImage = {}",bookImage.getOriginalFilename());
        if(!bookImage.isEmpty())
            dto.setImagePath(bookService.saveImage(bookImage));
        bookService.update(dto);
        model.addAttribute("bookDto",dto);
        return "book/detail";
    }
    @PostMapping("/removeBook")
    public String removeBook(@RequestParam("bookId")String bookId)
    {
        log.info("POST /bookboard/removeBook");
        bookService.removeBookImage(Long.parseLong(bookId));
        bookService.removeBook(bookId);
        return "book/modifybook";
    }

    @ResponseBody
    @PostMapping("/removeBookImage")
    public void removeBookImage(@RequestParam("bookId")long bookId)
    {
        log.info("POST /bookboard/removeBookImage");
        bookService.removeBookImage(bookId);
    }

    @PostMapping("/search")
    public String  search(@RequestParam String type,@RequestParam String word, Model model)
    {
        log.info("GET /bookboard/research");
        log.info("word = {}",word);
        log.info("type = {}",type);
        List<BookListDto> bookDtoList=new ArrayList<>();
        if(type.equals("bookName"))
        {
            List<Book> booksEntity = bookService.findByBookName(word);
            for(Book book:booksEntity)
            {
                bookDtoList.add(new BookListDto().entityToDto(book));
            }
        }
        else{
            List<Book> booksEntity = bookService.findByAuthorName(word);
            for(Book book:booksEntity)
            {
                bookDtoList.add(new BookListDto().entityToDto(book));
            }
        }
        log.info("bookDtoList = {}",bookDtoList);
        model.addAttribute("bookDtoList",bookDtoList);
        return "/";
    }

    @PostMapping("/purchase")
    public String purchase( BookPurchaseRequestDto dto, Model model)
    {
        log.info("POST /bookboard/purchase");
        log.info("dto = {}",dto);
        return "/index";
    }

    @GetMapping("/modifybook")
    private void modifybook(){
        log.info("GET /bookboard/modifybook");
    }

    @GetMapping("/rental")
    private void rental(){
        log.info("GET /bookboard/rental");
    }

    @GetMapping("/purchase")
    private void purchase(){
        log.info("GET /bookboard/purchase");
    }
}
