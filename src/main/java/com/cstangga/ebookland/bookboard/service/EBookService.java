package com.cstangga.ebookland.bookboard.service;

import com.cstangga.ebookland.bookboard.dto.BuyEBookDto;
import com.cstangga.ebookland.bookboard.entity.BuyEbook;
import com.cstangga.ebookland.bookboard.repository.BuyEbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EBookService {
    private final BuyEbookRepository buyEbookRepository;

    public BuyEbook buyEbook(BuyEBookDto dto) {
        log.info("BookService buyEbook");
        BuyEbook buyEbook=dto.dtoToEbookEntity();
        return buyEbookRepository.save(buyEbook);
    }

    public List<BuyEBookDto> findAllEBookById(long id) {
        log.info("BookService findEBookById");
        List<BuyEBookDto> buyEBookDtoList = new ArrayList<>();

        for(BuyEbook entity:buyEbookRepository.findAllByMemberId(id)){
            BuyEBookDto buyEBookDto=entity.toDto(entity);
            buyEBookDtoList.add(buyEBookDto);
        }
        return buyEBookDtoList;
    }
}
