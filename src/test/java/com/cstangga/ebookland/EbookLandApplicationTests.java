package com.cstangga.ebookland;

import com.cstangga.ebookland.book.entity.Book;
import com.cstangga.ebookland.book.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class EbookLandApplicationTests {
    @Autowired
    private BookService bookService;

    EbookLandApplicationTests(BookService bookService) {
        this.bookService = bookService;
    }

    @Test
    void findByBookNameContaining() throws Exception{
        String type="author";
        String word="표지1";
        List<Book> books=bookService.findByBookName(word);
        System.out.println("books = " + books);
    }

}
