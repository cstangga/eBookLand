package com.cstangga.ebookland.book.entity;

import lombok.Getter;

@Getter
public enum BookGenre {
    ROMANCE("romance"),
    ACTION("action"),
    COMIC("comic"),
    MYSTERY("mystery"),
    DRAMA("drama"),
    HORROR("horror"),
    FANTASY("fantasy");

    private final String genre;
    BookGenre(String genreName) {
        this.genre = genreName;
    }

}
