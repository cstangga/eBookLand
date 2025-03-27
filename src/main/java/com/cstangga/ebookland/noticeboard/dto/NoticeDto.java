package com.cstangga.ebookland.noticeboard.dto;

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


    public NoticeDto(Notice entity){
        this.noticeId=entity.getId();
        this.title=entity.getTitle();
        this.contents=entity.getContents();
        this.createAt=entity.getCreateAt();
        this.views=entity.getViews();
        this.relativeTime=entity.relativeTime();

    }

    public Notice dtoToEntity() {
       return Notice.builder()
                .id(this.noticeId)
                .title(this.title)
                .contents(this.contents)
                .createAt(LocalDateTime.now())
                .views(this.views).build();
    }
}
