package com.cstangga.ebookland.bookboard.dto;

import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.BuyEbook;
import com.cstangga.ebookland.bookboard.entity.BuyPaperBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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


    public BuyEbook dtoToEbookEntity(Book book) {
        return BuyEbook.builder()
                .book(book)
                .memberId(memberId)
                .totalPrice(totalPrice)
                .build();
    }

}
