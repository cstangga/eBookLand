package com.cstangga.ebookland.book.dto;


import com.cstangga.ebookland.book.entity.Book;
import com.cstangga.ebookland.book.entity.BookGenre;
import com.cstangga.ebookland.book.entity.BookTransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRegistDto {
    private String bookName;
    private String authorName;
    private String publisherName;
    private int bookAmount;
    private Set<BookGenre> bookGenre;
    private int rentalPrice;
    private int purchasePrice;
    private String bookSummary;
    private String bookDetails;
    private Set<BookTransactionType> bookTransactionType;
    private String uploadPath;

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
                .imagePath(uploadPath)
                .bookSummary(bookSummary)
                .bookDetails(bookDetails)
                .build();
    }
}
