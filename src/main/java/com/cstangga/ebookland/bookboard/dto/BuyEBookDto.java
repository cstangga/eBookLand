package com.cstangga.ebookland.bookboard.dto;

import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.BuyEbook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyEBookDto {
    private long bookId;
    private long memberId;
    private long totalPrice;
    private String bookName;
    private LocalDateTime buyDate;
    private String imageName;
    private String buyOption;
    private String details;


    public BuyEbook dtoToEbookEntity(Book book) {
        return BuyEbook.builder()
                .book(book)
                .memberId(memberId)
                .totalPrice(totalPrice)
                .build();
    }

}
