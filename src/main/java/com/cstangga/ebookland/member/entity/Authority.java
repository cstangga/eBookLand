package com.cstangga.ebookland.member.entity;


import lombok.Getter;

@Getter
public enum Authority {
    ROLE_ADMIN("관리자"),
    ROLE_MEMBER("회원");

    private final String authority;

    Authority(String authority) {
        this.authority = authority;
    }
}
