package com.cstangga.ebookland.book.service;


import com.cstangga.ebookland.book.dto.BookRegistDto;
import com.cstangga.ebookland.book.entity.Book;
import com.cstangga.ebookland.book.entity.BookGenre;
import com.cstangga.ebookland.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void save(BookRegistDto dto) {
        Book entity=dto.dtoToEntity();
        log.info("entity = {}",entity);
        bookRepository.save(entity);
    }


    public String saveImage(MultipartFile bookImage) throws IOException {
        log.info("BookService saveImage");
        String uploadPath = "C:/eBookLand/src/main/resources/static/bookImage";

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

        return uploadPath; // Return the saved file name


    }
}
