package com.cstangga.ebookland.book.entity;

import lombok.Getter;

@Getter
public enum Purchase_status {
    ONLINE("온라인"),
    OFFLINE("오프라인");

    private final String purchaseStatus;
    Purchase_status(String purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }
}
