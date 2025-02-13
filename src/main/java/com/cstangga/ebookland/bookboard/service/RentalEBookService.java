package com.cstangga.ebookland.bookboard.service;

import com.cstangga.ebookland.bookboard.dto.RentalEBookDto;
import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.RentalEbook;
import com.cstangga.ebookland.bookboard.repository.BookRepository;
import com.cstangga.ebookland.bookboard.repository.RentalBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RentalEBookService {
    private final RentalBookRepository rentalBookRepository;
    private final BookRepository bookRepository;

    public void rentalBook(RentalEBookDto dto) {
        log.info("BookService rentalBook");
        Book book=bookRepository.findBookById(dto.getBookId());
        RentalEbook entity = dto.dtoToRentalEbookEntity(book);
        entity=rentalBookRepository.save(entity);
        log.info("entity = {}",entity);
        log.info("대여 시작 날짜 = {}",entity.getCreateAt());
        log.info("대여 종료 날짜 = {}",entity.getExpirationDateTime());
    }

    public List<RentalEBookDto> findAllRentalEbookById(Long id) {
        // fetch join 써야 함
        // 회원이 대여한 모든 책 정보를 가져온다
        log.info("BookService findRentalEbookById");

        List<RentalEbook> rentalEbooks=rentalBookRepository.findAllByMemberId(id);
        List<RentalEBookDto> rentalEBookDtoList =new ArrayList<>();

        for(RentalEbook entity:rentalEbooks){

            Book bookEntity=bookRepository.findBookById(entity.getBook().getId());
            RentalEBookDto rentalEBookDto =entity.toDto();
            rentalEBookDtoList.add(rentalEBookDto);
            log.info("bookEntity = {}",bookEntity);
            log.info("rentalBookDto = {}", rentalEBookDto);
        }

        return rentalEBookDtoList;
    }

}
