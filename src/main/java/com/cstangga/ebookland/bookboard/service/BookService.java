package com.cstangga.ebookland.bookboard.service;


import com.cstangga.ebookland.bookboard.dto.BookModifyDto;
import com.cstangga.ebookland.bookboard.dto.BookRegistDto;
import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.BookGenre;
import com.cstangga.ebookland.bookboard.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.IOException;
import java.net.StandardProtocolFamily;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void save(Book book) {
        log.info("entity = {}",book);
        bookRepository.save(book);
    }

    public List<Book> findAll()
    {
        return bookRepository.findAll();
    }


    public String saveImage(MultipartFile bookImage) throws IOException {
        log.info("BookService saveImage");
        String uploadPath = "C:/ebookland/src/main/resources/static/bookImage";

        File directory = new File(uploadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate a unique filename to avoid conflicts
        String originalFilename = bookImage.getOriginalFilename();
        String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;

        // Save the file to the target location
        File destinationFile = new File(directory, uniqueFilename);
        bookImage.transferTo(destinationFile);

        return "/bookImage/"+uniqueFilename; // Return the saved file name
    }
    public void removeBookImage(long bookId) {
        log.info("BookService removeBookImage");
        Book book = bookRepository.findBookById(bookId);
        log.info("book = {}",book);
        book.setImagePath(null);
        bookRepository.save(book);
    }

    public List<Book> findByBookName(String bookName) {
        return bookRepository.findBookByBookNameContaining(bookName);
    }

    public List<Book> findByAuthorName(String authorName) {
        return bookRepository.findBookByAuthorNameContaining(authorName);
    }

    public Book findByBookId(long bookId) {
        return bookRepository.findBookById(bookId);
    }

    public void update(BookModifyDto dto) {
        Book entity = bookRepository.findBookById(dto.getBookId());
        entity.setBookName(dto.getBookName());
        entity.setAuthorName(dto.getAuthorName());
        entity.setBookDetails(dto.getBookDetails());
        entity.setBookDetails(dto.getBookDetails());
        entity.setAmount(dto.getBookAmount());
        entity.setBookGenres(dto.getBookGenre());
        entity.setBookTransactionTypes(dto.getBookTransactionType());
        entity.setPublisherName(dto.getPublisherName());
        entity.setRentalPrice(dto.getRentalPrice());
        entity.setPublisherName(dto.getPublisherName());
        if(!dto.getImagePath().isEmpty())
            entity.setImagePath(dto.getImagePath());
        bookRepository.save(entity);
    }

    public void removeBook(String bookId) {
        bookRepository.deleteById(bookId);
    }
}
