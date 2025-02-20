package com.cstangga.ebookland.chat.controller;

import com.cstangga.ebookland.chat.ChatRoom;
import com.cstangga.ebookland.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/create")
    public ChatRoom createRoom(@RequestParam String name) {
        log.info("POST /chat/createRoom");
        log.info("name: {}", name);
        ChatRoom chatRoom = chatService.createRoom(name);
        log.info("chatRoom: {}", chatRoom);
        return chatRoom;
    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        log.info("GET /chat/findAllRoom");
        return chatService.findAllRoom();
    }
}