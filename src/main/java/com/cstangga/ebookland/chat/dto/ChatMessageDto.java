package com.cstangga.ebookland.chat.dto;

import com.cstangga.ebookland.chat.entity.MessageType;
import com.cstangga.ebookland.member.controller.MemberController;
import com.cstangga.ebookland.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessageDto {
    private MessageType type; // 메시지 타입
    private long roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
    private String  createdAt;
}