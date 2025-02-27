package com.cstangga.ebookland.chat.entity;

import lombok.Getter;

@Getter
public enum MessageType {
    EXIT("exit"),
    ENTER("enter"),
    TALK("talk");

    private final String type;
    MessageType(String type) {
        this.type = type;
    }
}
