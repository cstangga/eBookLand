package com.cstangga.ebookland.chat.dto;

import com.cstangga.ebookland.chat.entity.ChatRoom;
import com.cstangga.ebookland.member.entity.Member;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDto {
    // pub/sub 방식을 이용하면 자동으로 세션관리과 발송도 구현하기 때문에
    // 전에 쓰던 WebSocket 코드는 사용하지 않는데 (맨 아래 주석 처리 됨)

    private long roomId;
    private String roomName; // 방 제목인데, 1:1 채팅이니 "홍길동"님 과의 채팅 이렇게 하자
    private Member member;
    private Member admin;
    private ChatMessageDto chatMessage;


    public ChatRoom toEntity() {
        return ChatRoom.builder()
                .admin(admin)
                .member(member)
                .roomName(member.getNickName())
                .createAt(member.getCreatedAt()) // 멤버가 생성되자마자 채팅룸이 만들어진다.
                .build();
    }


    /**
    // chatMessage 타입으로 구별 (ENTER, TALK )
    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session); // 이 부분을 해야만 채팅을 할 수 있다.
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
        }
        sendMessage(chatMessage, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
     **/
}
