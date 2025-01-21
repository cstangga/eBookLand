package com.cstangga.ebookland.book.controller;

import com.cstangga.ebookland.auth.principal.AuthPrincipal;
import com.cstangga.ebookland.book.dto.BookListDto;
import com.cstangga.ebookland.book.dto.BookModifyDto;
import com.cstangga.ebookland.book.dto.BookRegistDto;
import com.cstangga.ebookland.book.entity.Book;
import com.cstangga.ebookland.book.service.BookService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        dto.setImagePath(bookService.saveImage(bookImage));
        Book entity=dto.dtoToEntity();
        bookService.save(entity);

    }

    @GetMapping("/detail/{bookId}") // 일 시작 게시글 보여주는 핸들러 + 리뷰이미지까지 넣어야 한다
    public String detail(@PathVariable("bookId") long bookId, Model model)
    {
        log.info("GET /book/detail/{}",bookId);
        BookModifyDto bookDto=new BookModifyDto().entityToDto(bookService.findByBookId(bookId));
        log.info("bookDto = {}",bookDto);
        model.addAttribute("bookDto",bookDto);
        return "book/detail";
    }

    @PostMapping("/update") // 도서 상태 업데이트
    public String modify(BookModifyDto dto, Model model,@RequestParam("bookImage")MultipartFile bookImage) throws IOException
    {
        log.info("POST /book/modify");
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
        log.info("POST /book/removeBook");
        bookService.removeBook(bookId);
        return "book/modifybook";
    }

    @ResponseBody
    @PostMapping("/removeBookImage")
    public void removeBookImage(@RequestParam("bookId")long bookId)
    {
        log.info("POST /book/removeBookImage");
        bookService.removeBookImage(bookId);
    }

    @PostMapping("/search") // 일 시작 게시글 보여주는 핸들러 + 리뷰이미지까지 넣어야 한다
    public String  search(@RequestParam String type,@RequestParam String word, Model model)
    {
        log.info("GET /book/research");
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
        return "/book/modifybook";
    }

    @GetMapping("/modifybook")
    private void modifybook(){
        log.info("GET /book/modifybook");
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
