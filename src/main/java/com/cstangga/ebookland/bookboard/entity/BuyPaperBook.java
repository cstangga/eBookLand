package com.cstangga.ebookland.bookboard.entity;

import com.cstangga.ebookland.bookboard.dto.AllBooksInfoDto;
import com.cstangga.ebookland.bookboard.dto.BuyPaperBookDto;
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
@Table(name = "tbl_buy_paperbook")
public class BuyPaperBook {

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

    @Column(name = "total_price")
    private long totalPrice;

    @Column(name = "total_amount")
    private int totalAmount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // BookEntity과 삭제되면 같이 삭제 됨
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @Version
    private long version;

    public BuyPaperBookDto toDto() {
        return BuyPaperBookDto.builder()
                .bookId(book.getId())
                .memberId(memberId)
                .totalAmount(totalAmount)
                .totalPrice(totalPrice).build();
    }

    public AllBooksInfoDto toInfoDto() {
        return AllBooksInfoDto.builder()
                .bookId(book.getId())
                .buyBuyOptions("종이책 구매")
                .buyDate(createAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .imageName(book.getImageName())
                .bookName(book.getBookName())
                .totalAmount(totalAmount)
                .totalPrice(totalPrice).build();
    }
}
