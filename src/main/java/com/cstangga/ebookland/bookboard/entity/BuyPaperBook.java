package com.cstangga.ebookland.bookboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


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

    @org.springframework.data.annotation.Version
    private long version;

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

    @Column(name = "total_amount")
    private long totalAmount;

    public void decrease(long buyAmount)
    {
        totalAmount -= buyAmount;
    }
}
