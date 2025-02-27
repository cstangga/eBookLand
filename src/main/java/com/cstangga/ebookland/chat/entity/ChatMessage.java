package com.cstangga.ebookland.chat.entity;

import com.cstangga.ebookland.chat.dto.ChatMessageDto;
import com.cstangga.ebookland.member.entity.Member;
import groovy.transform.builder.Builder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "chat_message")
@Table(name = "tbl_chat_message")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;  // 메시지 기본키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom; // 채팅방 (ManyToOne 관계)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private Member sender; // 보낸 사람

    @Column(nullable = false, length = 500)
    private String message; // 메시지 내용

    @Column(name = "message_type")
    private MessageType messageType;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt; // 메시지 전송 시간


    public ChatMessageDto toDto() {
        return ChatMessageDto.builder()
                .message(message)
                .roomId(chatRoom.getId())
                .sender(sender.getNickName())
                .type(MessageType.TALK)
                .createdAt(createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).build();
    }
}
