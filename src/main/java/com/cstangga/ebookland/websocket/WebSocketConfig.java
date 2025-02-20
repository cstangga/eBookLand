package com.cstangga.ebookland.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
@Slf4j
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;

    // 웹소켓 통신을 열어주는 메소드
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        log.info("registerWebSocketHandlers");
        registry.addHandler(webSocketHandler, "/ws/chat")
                .setAllowedOriginPatterns("*");
//        .withSockJS(); WebSocket을 지원하지 않는 브라우저나 환경에서 쓰기 위한 용도 (현재는 거의 쓰지 않는다)
    }
}