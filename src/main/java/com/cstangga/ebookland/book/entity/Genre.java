package com.cstangga.ebookland.book.entity;

import lombok.Getter;

@Getter
public enum Genre {
    ROMANCE("로맨스"),
    ACTION("액션"),
    COMIC("코믹"),
    MYSTERY("추리"),
    DRAMA("드라마"),
    HORROR("공포"),
    FANTASY("판타지");

    private final String genre;
    Genre(String genreName) {
        this.genre = genreName;
    }

}
