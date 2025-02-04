package com.cstangga.ebookland.bookboard.dto;


import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.BookGenre;
import com.cstangga.ebookland.bookboard.entity.BookTransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookModifyDto {
    private long bookId;
    private String bookName;
    private String authorName;
    private String publisherName;
    private int bookAmount;
    private Set<BookGenre> bookGenre;
    private double rentalPrice;
    private double purchasePrice;
    private String bookSummary;
    private String bookDetails;
    private Set<BookTransactionType> bookTransactionType;
    private String imagePath;

    public BookModifyDto entityToDto(Book book) {
        return BookModifyDto.builder()
                .bookId(book.getId())
                .bookName(book.getBookName())
                .authorName(book.getAuthorName())
                .publisherName(book.getPublisherName())
                .bookAmount(book.getAmount())
                .bookGenre(book.getBookGenres())
                .rentalPrice(book.getRentalPrice())
                .purchasePrice(book.getPurchasePrice())
                .bookSummary(book.getBookSummary())
                .bookDetails(book.getBookDetails())
                .bookTransactionType(book.getBookTransactionTypes())
                .imagePath(book.getImagePath())
                .build();
    }
}
