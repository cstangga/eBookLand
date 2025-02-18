package com.cstangga.ebookland.freeboard.dto;


import com.cstangga.ebookland.freeboard.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private String comment;
    private String nickname;
    private Reply reply;
    private LocalDateTime createdAt;

}
