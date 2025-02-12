package com.cstangga.ebookland.bookboard.dto;


import com.cstangga.ebookland.bookboard.entity.SellsOptions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllBooksInfoDto {
    private String bookName;
    private String authorName;
    private long bookId;
    private String publisher;
    private long memberId;
    private long totalPrice;
    private long totalAmount;
    private SellsOptions buyBuyOptions;
    private String buyDate; // 구매날짜 or 대여시작 날짜
    private String expiryDate; // 대여 종료 날짜
    private long remainingDays;
    private long remainingTime;
    private String imageName;

}
