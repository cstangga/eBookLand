package com.cstangga.ebookland.bookboard.entity;

import lombok.Getter;

@Getter
public enum SellsOptions {
    RENTAL_EBOOK("rental_ebook"),
    EBOOK("eBook"),
    PAPER_BOOK("paper_book"),;

    private final String sellsOption;
    SellsOptions(String sellsOption) {
        this.sellsOption = sellsOption;
    }
}
