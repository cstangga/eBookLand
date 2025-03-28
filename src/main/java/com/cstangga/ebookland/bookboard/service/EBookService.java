package com.cstangga.ebookland.bookboard.service;

import com.cstangga.ebookland.bookboard.dto.BuyEBookDto;
import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.BuyEbook;
import com.cstangga.ebookland.bookboard.repository.BookRepository;
import com.cstangga.ebookland.bookboard.repository.BuyEbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EBookService {
    private final BuyEbookRepository buyEbookRepository;
    private final BookRepository bookRepository;

    public boolean duplicatedCheck(BuyEBookDto dto){
        Optional<Book> book= Optional.ofNullable(bookRepository.findBookById(dto.getBookId()));
        return book.isEmpty();

    }

    public BuyEbook buyEbook(BuyEBookDto dto) {
        log.info("BookService buyEbook");

        Book book=bookRepository.findBookById(dto.getBookId());
        BuyEbook buyEbook=dto.dtoToEbookEntity(book);
        return buyEbookRepository.save(buyEbook);
    }

    public List<BuyEBookDto> findAllEBookById(long id) {
        log.info("BookService findEBookById");
        log.info("findAllByMemberId = {}",buyEbookRepository.findAllByMemberId(id));
        List<BuyEBookDto> buyEBookDtoList = new ArrayList<>();

        for(BuyEbook entity:buyEbookRepository.findAllByMemberId(id)){
            BuyEBookDto buyEBookDto=entity.toDto();
            buyEBookDtoList.add(buyEBookDto);
        }
        return buyEBookDtoList;
    }

    public BuyEBookDto findEBookByBookId(long bookId) {
        log.info("BookService findEBookByBookId");
        BuyEbook entity=buyEbookRepository.findBuyEbookByBookId(bookId);
        return entity.toDto();
    }
}
