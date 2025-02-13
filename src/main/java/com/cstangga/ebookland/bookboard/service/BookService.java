package com.cstangga.ebookland.bookboard.service;


import com.cstangga.ebookland.bookboard.dto.AllBooksInfoDto;
import com.cstangga.ebookland.bookboard.dto.BookListDto;
import com.cstangga.ebookland.bookboard.dto.BookModifyDto;
import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.BuyEbook;
import com.cstangga.ebookland.bookboard.entity.BuyPaperBook;
import com.cstangga.ebookland.bookboard.entity.RentalEbook;
import com.cstangga.ebookland.bookboard.repository.BookRepository;
import com.cstangga.ebookland.bookboard.repository.BuyEbookRepository;
import com.cstangga.ebookland.bookboard.repository.BuyPaperBookRepository;
import com.cstangga.ebookland.bookboard.repository.RentalBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BuyEbookRepository buyEbookRepository;
    private final RentalBookRepository rentalBookRepository;
    private final BuyPaperBookRepository buyPaperBookRepository;

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

        // 프로젝트의 절대 경로를 동적으로 가져오기
        String uploadPath = new File("src/main/resources/static/bookImage").getAbsolutePath();

        // 디렉토리 생성 (존재하지 않으면 생성)
        File directory = new File(uploadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 고유한 파일 이름 생성 (UUID 사용)
        String originalFilename = bookImage.getOriginalFilename();
        String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;

        // 파일 저장
        File destinationFile = new File(directory, uniqueFilename);
        bookImage.transferTo(destinationFile);

        // 반환할 경로 (정적 리소스 URL 경로)
        return uniqueFilename;
    }

    public void removeBookImage(long bookId) {
        log.info("BookService removeBookImage");
        Book book = bookRepository.findBookById(bookId);
        String imageName=book.getImageName();
        log.info("book = {}",book);

        try {
            // book.getimageName()로 실제 파일 경로 설정
            String uploadPath = new File("src/main/resources/static/bookImage").getAbsolutePath();
            Path path = Paths.get(uploadPath, imageName);

            log.info("path = {}",path);
            log.info("imageName = {}",imageName);

            // 파일이 존재하면 삭제
            if (Files.exists(path)) {
                Files.delete(path);
                log.info("이미지 삭제 완료: {}", path);
            } else {
                log.warn("파일이 존재하지 않음: {}", path);
            }
        } catch (Exception e) {
            log.error("이미지 삭제 중 오류 발생", e);
        }
        book.setImageName(null);
        bookRepository.save(book);
    }

    public List<Book> findByBookName(String bookName) {
        log.info("BookService findByBookName");
        return bookRepository.findBookByBookNameContaining(bookName);
    }

    public List<Book> findByAuthorName(String authorName) {
        log.info("BookService findByAuthorName");
        return bookRepository.findBookByAuthorNameContaining(authorName);
    }

    public Book findByBookId(long bookId) {
        return bookRepository.findBookById(bookId);
    }

    public void update(BookModifyDto dto) {
        log.info("bookService update");
        log.info("dto = {}",dto);
        Book entity = bookRepository.findBookById(dto.getBookId());
        entity.update(dto);
        bookRepository.save(entity);
    }

    public void removeBook(String bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<BookListDto> searchBook(String type, String word) {
        log.info("BookService searchBook");
        List<BookListDto> bookListDto = new ArrayList<>();
        if(type.equals("bookName"))
        {
            List<Book> booksEntity =bookRepository.findBookByBookNameContaining(word);
            for(Book book:booksEntity)
            {
                bookListDto.add(new BookListDto().entityToDto(book));
            }
        }
        else{
            List<Book> booksEntity = bookRepository.findBookByAuthorNameContaining(word);
            for(Book book:booksEntity)
            {
                bookListDto.add(new BookListDto().entityToDto(book));
            }
        }
        return bookListDto;
    }

    public List<AllBooksInfoDto> findAllBook(long id)
    {
        log.info("bookService findAllBook");
        List<AllBooksInfoDto> allBooksInfoDtoList = new ArrayList<>();
        for(BuyPaperBook entity : buyPaperBookRepository.findAllByMemberId(id))
        {
            Book book = bookRepository.findBookById(entity.getBookId());
            AllBooksInfoDto dto = entity.toInfoDto(entity);
            dto.setBookName(book.getBookName());
            dto.setImageName(book.getImageName());
            allBooksInfoDtoList.add(dto);
        }

        for(BuyEbook entity : buyEbookRepository.findAllByMemberId(id))
        {
            Book book = bookRepository.findBookById(entity.getBookId());
            AllBooksInfoDto dto = entity.toInfoDto(entity);
            dto.setBookName(book.getBookName());
            dto.setImageName(book.getImageName());
            allBooksInfoDtoList.add(dto);
        }

        for(RentalEbook entity : rentalBookRepository.findAllByMemberId(id))
        {
            Book book = bookRepository.findBookById(entity.getBookId());
            AllBooksInfoDto dto = entity.toInfoDto(entity);
            dto.setBookName(book.getBookName());
            dto.setImageName(book.getImageName());
            allBooksInfoDtoList.add(dto);
        }
        return allBooksInfoDtoList;
    }
}
