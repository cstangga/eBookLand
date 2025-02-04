package com.cstangga.ebookland.bookboard.entity;

import lombok.Getter;

@Getter
public enum Purchase_status {
    RENTAL("rental"),
    ONLINE("online"),
    OFFLINE("offline");

    private final String purchaseStatus;
    Purchase_status(String purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }
}
