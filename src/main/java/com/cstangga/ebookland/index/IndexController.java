package com.cstangga.ebookland.index;

import com.cstangga.ebookland.bookboard.dto.BookDto;
import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class IndexController {
    private final BookService bookService;

    @GetMapping("/")
    public String index( Model model) {
        List<BookDto> bookDtoList=new ArrayList<>();
        List<Book> booksEntity = bookService.findAll();
        for(Book book:booksEntity)
        {
            bookDtoList.add(new BookDto().entityToDto(book));
        }
        model.addAttribute("bookDtoList",bookDtoList);
        return "index";
    }
}
