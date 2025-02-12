package com.cstangga.ebookland.bookboard.dto;

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
    private LocalDateTime buyDate;


    public BuyEbook dtoToEbookEntity() {
        return BuyEbook.builder()
                .bookId(bookId)
                .memberId(memberId)
                .totalPrice(totalPrice)
                .build();
    }

}
