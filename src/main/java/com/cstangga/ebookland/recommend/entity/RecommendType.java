package com.cstangga.ebookland.recommend.entity;

import lombok.Getter;

@Getter
public enum RecommendType {
    LIKES("likes"), DISLIKES("disLikes");

    private final String recommendType;

    RecommendType(String recommendType)
    {
        this.recommendType = recommendType;
    }
}
