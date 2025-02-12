package com.cstangga.ebookland.bookboard.dto;


import com.cstangga.ebookland.bookboard.entity.RentalEbook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalBookDto {

    private long bookId;
    private long rentalId;
    private long memberId;
    private int totalPrice;
    private int totalAmount;
    private int rentalDays;
    private String bookName;
    private LocalDateTime rentalStartDate;
    private LocalDateTime rentalEndDate;
    private long remainingDay; // 며칠 남았는지
    private long remainingHours; // 몇시간 남았는지
    private long remainingMinutes; // 몇시간 남았는지


    public RentalEbook dtoToRentalEbook() {
        return RentalEbook.builder()
                .bookId(bookId)
                .memberId(memberId)
                .totalPrice(totalPrice)
                .expirationDateTime(LocalDateTime.now().plusDays(rentalDays)).build();
    }
}
