package com.cstangga.ebookland.bookboard.dto;


import com.cstangga.ebookland.bookboard.entity.BuyEbook;
import com.cstangga.ebookland.bookboard.entity.BuyPaperBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyPaperBookDto {
    private long bookId;
    private long memberId;
    private long totalPrice;
    private int totalAmount;

    public BuyPaperBook dtoToPaperBookEntity()
    {
        return BuyPaperBook.builder()
                .bookId(bookId)
                .memberId(memberId)
                .totalPrice(totalPrice)
                .totalAmount(totalAmount).build();
    }
}