package com.cstangga.ebookland.book.entity;

import lombok.Getter;

@Getter
public enum BookTransactionType {
    RENTAL("rental"),
    OFFLINE_PURCHASE("offline"),
    ONLINE_PURCHASE("online");

    private final String transactionType;
    BookTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
