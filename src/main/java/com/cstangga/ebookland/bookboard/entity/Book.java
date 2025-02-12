package com.cstangga.ebookland.bookboard.entity;

import com.cstangga.ebookland.bookboard.dto.BookModifyDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "book")
@Table(name = "tbl_book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author_name") // 책 저자 이름
    private String authorName;

    @Column(name = "publisher_name") // 출판사
    private String publisherName;

    @Column(name = "amount") // 재고
    private int amount;

    @Column(name = "bookDetails", columnDefinition = "LONGTEXT")
    private String bookDetails;

    @Column(name = "bookSummary", columnDefinition = "LONGTEXT")
    private String bookSummary;

    @Column(name = "rental_price") // 렌탈 시 가격
    private double rentalPrice;

    @Column(name = "purchase_price") // 구매 시 가격
    private double purchasePrice;

    @Version
    private long version;

    @Column(name = "image_path") // 이미지 경로
    private String imageName;

    // 책에 장르
    @Enumerated(EnumType.STRING) // enum을 string으로 관리, int로 관리하면 직관성이 떨어짐
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "tbl_genre",
            joinColumns = @JoinColumn(name = "book_id") //
    )
    @Column(name = "genre")
    private Set<BookGenre> bookGenres = new HashSet<>();


    // 판매 방법
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "tbl_sells_options",
            joinColumns = @JoinColumn(name = "book_id") //
    )
    private Set<SellsOptions> sellsOptions =new HashSet<>();

    public void update(BookModifyDto dto)
    {
        this.bookName=dto.getBookName();
        this.authorName=dto.getAuthorName();
        this.publisherName=dto.getPublisherName();
        this.bookDetails=dto.getBookDetails();
        this.bookSummary=dto.getBookSummary();
        this.rentalPrice=dto.getRentalPrice();
        this.purchasePrice=dto.getPurchasePrice();
        this.imageName =dto.getImageName();
        this.bookGenres=dto.getBookGenre();
        this.sellsOptions =dto.getSellsOptions();
        if(!dto.getImageName().isEmpty())
            this.imageName =dto.getImageName();
    }

    public int decrease(int amount)
    {
        return this.amount-=amount;
    }
}
