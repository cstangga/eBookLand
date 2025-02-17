package com.cstangga.ebookland.bookboard.entity;

import com.cstangga.ebookland.bookboard.dto.AllBooksInfoDto;
import com.cstangga.ebookland.bookboard.dto.RentalEBookDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_rental_ebook")
public class RentalEbook {

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

    @Column(name = "Expiration_date_time")
    private LocalDateTime expirationDateTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // BookEntity 와 삭제되면 같이 삭제 됨
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;


    public RentalEBookDto toDto() {
        Duration duration=Duration.between(LocalDateTime.now(), expirationDateTime);
        long remainingDays=duration.toDays();
        long remainingHours=duration.toHours()%24;
        long remainingMinutes=duration.toMinutes()%60;

        return RentalEBookDto.builder()
                .memberId(memberId)
                .bookId(book.getId())
                .rentalId(this.id)
                .bookName(book.getBookName())
                .imageName(book.getImageName())
                .details(book.getBookDetails())
                .buyOption("전자책 대여")
                .rentalStartDate(this.createAt)
                .rentalEndDate(this.expirationDateTime)
                .remainingStatus(LocalDateTime.now().compareTo(expirationDateTime)) // 조회날짜 - 만료날짜
                .remainingDays(remainingDays)
                .remainingHours(remainingHours)
                .remainingMinutes(remainingMinutes).build();
    }

    public AllBooksInfoDto toInfoDto() {
        Duration duration=Duration.between(LocalDateTime.now(), expirationDateTime);
        long remainingDays=duration.toDays();
        long remainingHours=duration.toHours()%24;
        long remainingMinutes=duration.toMinutes()%60;

        return AllBooksInfoDto.builder()
                .bookId(book.getId())
                .imageName(book.getImageName())
                .bookName(book.getBookName())
                .buyDate(createAt.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .expiryDate(expirationDateTime.format(  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .remainingDays(remainingDays)
                .remainingHours(remainingHours)
                .remainingMinutes(remainingMinutes)
                .remainingStatus(LocalDateTime.now().compareTo(expirationDateTime)) // 조회날짜 - 만료날짜
                .buyBuyOptions("전자책 대여")
                .totalPrice(totalPrice).build();
    }

}
