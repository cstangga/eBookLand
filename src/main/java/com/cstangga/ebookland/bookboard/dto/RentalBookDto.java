package com.cstangga.ebookland.bookboard.dto;


import com.cstangga.ebookland.bookboard.entity.RentalEbook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalBookDto {

    private long bookId;
    private long memberId;
    private int totalPrice;
    private int totalAmount;
    private int rentalDays;


    public RentalEbook dtoToRentalEbook() {
        return RentalEbook.builder()
                .bookId(bookId)
                .memberId(memberId)
                .totalPrice(totalPrice)
                .expirationDateTime(LocalDateTime.now().plusDays(rentalDays)).build();
    }
}
