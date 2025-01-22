package com.cstangga.ebookland.bookboard.entity;

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

    @Column(name = "image_path") // 이미지 경로
    private String imagePath;

    // 책에 장르
    @Enumerated(EnumType.STRING) // enum을 string으로 관리, int로 관리하면 직관성이 떨어짐
    @ElementCollection(fetch = FetchType.EAGER) // 컬렉션 객체임을 jpa가 알 수 있게 해줌, 지연 로딩
    @CollectionTable(
            name = "tbl_genre",
            joinColumns = @JoinColumn(name = "book_id") //
    )
    private Set<BookGenre> bookGenres = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "tbl_transactiontype",
            joinColumns = @JoinColumn(name = "book_id") //
    )
    private Set<BookTransactionType> bookTransactionTypes=new HashSet<>();

}
