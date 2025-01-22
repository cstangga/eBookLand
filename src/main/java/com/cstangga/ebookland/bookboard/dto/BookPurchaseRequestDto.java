package com.cstangga.ebookland.bookboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPurchaseRequestDto {
    private long bookId;
    private long memberId;
    private String purchaseType;
    private int totalPrice;
    private int totalAmount;
    private String partnerOrderId;

    private String tax_free_amount;
}
