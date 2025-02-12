package com.cstangga.ebookland.bookboard.entity;

import com.cstangga.ebookland.bookboard.dto.AllBooksInfoDto;
import com.cstangga.ebookland.bookboard.dto.BuyEBookDto;
import com.cstangga.ebookland.bookboard.dto.RentalBookDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

    @Column(name = "book_id")
    private long bookId;

    @Column(name = "total_price")
    private long totalPrice;

    @Column(name = "Expiration_date_time")
    private LocalDateTime expirationDateTime;

    public RentalBookDto toDto(Book entity) {
        long remainingDays=createAt.until(expirationDateTime, ChronoUnit.DAYS);
        long remainingHours=createAt.until(expirationDateTime, ChronoUnit.HOURS);
        long remainingMinutes=createAt.until(expirationDateTime, ChronoUnit.MINUTES);
        return RentalBookDto.builder()
                .memberId(entity.getId())
                .bookId(entity.getId())
                .rentalId(this.id)
                .rentalStartDate(this.createAt)
                .rentalEndDate(this.expirationDateTime)
                .remainingDay(remainingDays)
                .remainingHours(remainingHours)
                .remainingMinutes(remainingMinutes).build();
    }

    public AllBooksInfoDto toInfoDto(RentalEbook entity) {
        long remainingDays=createAt.until(expirationDateTime, ChronoUnit.DAYS);
        long remainingHours=createAt.until(expirationDateTime, ChronoUnit.HOURS);

        return AllBooksInfoDto.builder()
                .bookId(entity.getBookId())
                .buyBuyOptions(SellsOptions.PAPER_BOOK)
                .buyDate(entity.getCreateAt().format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .expiryDate(entity.getExpirationDateTime().format(  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .totalPrice(entity.getTotalPrice()).build();
    }
}
