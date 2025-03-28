package com.cstangga.ebookland.noticeboard.dto;

import com.cstangga.ebookland.member.entity.Member;
import com.cstangga.ebookland.noticeboard.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NoticeDto {
    private long noticeId;
    private String title;
    private String contents;
    private LocalDateTime createAt;
    private long views;
    private String relativeTime;
    private long likes;
    private long disLikes;
    private Member member;
    private Notice notice;

    // 생성, 수정 할때만 사용하지 않을까??
    public Notice dtoToEntity() {
       return Notice.builder()
                .id(this.noticeId)
                .title(this.title)
                .contents(this.contents)
               .build();
    }
}
