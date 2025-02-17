package com.cstangga.ebookland.bookboard.entity;


import com.cstangga.ebookland.bookboard.dto.AllBooksInfoDto;
import com.cstangga.ebookland.bookboard.dto.BuyEBookDto;
import com.cstangga.ebookland.bookboard.dto.MyBookDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // BookEntity과 삭제되면 같이 삭제 됨
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @Column(name = "total_price")
    private long totalPrice;

    public BuyEBookDto toDto() {
        return BuyEBookDto.builder()
                .bookId(book.getId())
                .imageName(book.getImageName())
                .bookName(book.getBookName())
                .memberId(memberId)
                .totalPrice(totalPrice)
                .buyOption("전자책 구매")
                .details(book.getBookDetails())
                .buyDate(createAt).build();
    }

    public AllBooksInfoDto toInfoDto() {
        return AllBooksInfoDto.builder()
                .bookName(book.getBookName())
                .bookId(book.getId())
                .imageName(book.getImageName())
                .bookName(book.getBookName())
                .buyBuyOptions("전자책 구매")
                .buyDate(createAt.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .totalPrice(totalPrice).build();
    }

    public MyBookDto toMyBookDto() {
        return MyBookDto.builder()
                .bookId(book.getId())
                .buyBuyOptions("전자책 구매")
                .buyDate(createAt.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .totalPrice(totalPrice).build();
    }


}
