package com.cstangga.ebookland.bookboard.service;

import com.cstangga.ebookland.bookboard.dto.BuyEBookDto;
import com.cstangga.ebookland.bookboard.dto.BuyPaperBookDto;
import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.BuyEbook;
import com.cstangga.ebookland.bookboard.entity.BuyPaperBook;
import com.cstangga.ebookland.bookboard.repository.BookRepository;
import com.cstangga.ebookland.bookboard.repository.BuyEbookRepository;
import com.cstangga.ebookland.bookboard.repository.BuyPaperBookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.OptimisticLock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaperBookService {
    private final BuyPaperBookRepository buyPaperBookRepository;
    private final BookRepository bookRepository;
    private final BuyEbookRepository buyEbookRepository;

    @Transactional
    public boolean buyPaperBook(BuyPaperBookDto dto) {
        // 낙관적 락 걸어야 함
        Book book = bookRepository.findBookByIdForUpdate(dto.getBookId());
        if(book.getAmount()<dto.getTotalAmount())
        {
            return false;
        }
        else{
            book.decrease(dto.getTotalAmount());
            BuyPaperBook buyPaperBook = dto.dtoToPaperBookEntity();
            buyPaperBookRepository.save(buyPaperBook);
            return true;
        }
    }

    public List<BuyPaperBookDto> findAllPaperBookById(long memberId) {
        List<BuyPaperBookDto> buyPaperBookDtoList = new ArrayList<>();

        for(BuyPaperBook entity:buyPaperBookRepository.findAllByMemberId(memberId)) {
            BuyPaperBookDto buyPaperBookDto = entity.toDto(entity);
            buyPaperBookDtoList.add(buyPaperBookDto);
        }
        return buyPaperBookDtoList;
    }
}
