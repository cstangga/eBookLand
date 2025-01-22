package com.cstangga.ebookland.bookboard.dto;


import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.BookGenre;
import com.cstangga.ebookland.bookboard.entity.BookTransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRegistDto {
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

    public Book dtoToEntity(){
        return  Book.builder()
                .bookName(bookName)
                .authorName(authorName)
                .publisherName(publisherName)
                .amount(bookAmount)
                .rentalPrice(rentalPrice)
                .purchasePrice(purchasePrice)
                .bookTransactionTypes(bookTransactionType)
                .bookGenres(bookGenre)
                .imagePath(imagePath)
                .bookSummary(bookSummary)
                .bookDetails(bookDetails)
                .build();
    }
}
