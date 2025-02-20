package com.cstangga.ebookland.websocket;

import com.cstangga.ebookland.chat.ChatRoom;
import com.cstangga.ebookland.chat.dto.ChatMessage;
import com.cstangga.ebookland.chat.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    //서버핸들러 : 클라이언트가 발송한 메시지를 받아 처리해주는 Handler.
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload(); // json형식으로 보여준다
        log.info("payload {}", payload);
        ChatMessage chatMessage=objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom chatRoom=chatService.findRoomById(chatMessage.getRoomId());
        chatRoom.handleActions(session,chatMessage,chatService);
    }
}