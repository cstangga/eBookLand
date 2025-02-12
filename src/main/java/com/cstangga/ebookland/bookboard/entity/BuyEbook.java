package com.cstangga.ebookland.bookboard.entity;


import com.cstangga.ebookland.bookboard.dto.AllBooksInfoDto;
import com.cstangga.ebookland.bookboard.dto.BuyEBookDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_buy_ebook")
public class BuyEbook {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Column(name = "member_id")
    private long memberId;

    @Column(name = "book_id")
    private long bookId;

    @Column(name = "total_price")
    private long totalPrice;

    public BuyEBookDto toDto(BuyEbook entity) {
        return BuyEBookDto.builder()
                .bookId(entity.bookId)
                .memberId(entity.memberId)
                .totalPrice(entity.totalPrice)
                .buyDate(entity.createAt).build();
    }

    public AllBooksInfoDto toInfoDto(BuyEbook entity) {
        return AllBooksInfoDto.builder()
                .bookId(entity.getBookId())
                .buyBuyOptions(SellsOptions.PAPER_BOOK)
                .buyDate(entity.getCreateAt().format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .totalPrice(entity.getTotalPrice()).build();
    }


}
