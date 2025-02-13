package com.cstangga.ebookland.bookboard.dto;


import com.cstangga.ebookland.bookboard.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDtoList {
    private long bookId;
    private String bookName;
    private String author;
    private String publisher;
    private String imageName;

    public BookDtoList entityToDto(Book entity) {
        return BookDtoList.builder()
                .bookId(entity.getId())
                .bookName(entity.getBookName())
                .author(entity.getAuthorName())
                .publisher(entity.getPublisherName())
                .imageName(entity.getImageName())
                .build();
    }
}
