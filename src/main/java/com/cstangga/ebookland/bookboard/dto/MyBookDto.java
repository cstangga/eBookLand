package com.cstangga.ebookland.bookboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyBookDto {
    private String bookName;
    private String authorName;
    private long bookId;
    private String publisher;
    private long memberId;
    private long totalPrice;
    private long totalAmount;
    private String  buyBuyOptions;
    private String buyDate; // 구매날짜 or 대여시작 날짜
    private String expiryDate; // 대여 종료 날짜
    private long remainingDays; // 남은 날짜
    private long remainingHours; // 남은시간
    private long remainingMinutes; // 남은시간
    private long remainingStatus; // 남은 시간 및 날짜가 잇는지 확인
    private String imageName;

}