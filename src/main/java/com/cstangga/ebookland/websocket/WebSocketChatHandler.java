package com.cstangga.ebookland.websocket;

/**
 * stomp의 pub/sub 사용하기 때문에 메세지를 다루는 핸들러는 없어도 된다.
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
        ChatRoomDto chatRoomDto =chatService.findRoomById(chatMessage.getRoomId());
        chatRoomDto.handleActions(session,chatMessage,chatService);
    }
}
 **/