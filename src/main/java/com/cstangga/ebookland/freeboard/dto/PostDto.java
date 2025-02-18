package com.cstangga.ebookland.freeboard.dto;


import com.cstangga.ebookland.freeboard.entity.Post;
import com.cstangga.ebookland.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostDto {
    private long postId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private int views;
    private int likes;
    private Member member;
    private long memberId;
    private String relativeTime;


    public Post dtoToEntity(Member member) {
        return Post.builder()
                .member(member)
                .title(title)
                .content(content)
                .build();
    }

}
