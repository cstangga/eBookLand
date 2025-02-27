package com.cstangga.ebookland.chat.controller;

import com.cstangga.ebookland.chat.dto.ChatMessageDto;
import com.cstangga.ebookland.chat.dto.ChatRoomDto;
import com.cstangga.ebookland.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/message")
    public void sendMessage(@ModelAttribute ChatMessageDto dto)
    {
        log.info("dto = {}",dto);
        chatService.sendMessage(dto);
    }

    @GetMapping("/list")
    public List<ChatRoomDto> findAllRoom() {
        log.info("GET /chat/findAllRoom");
        return chatService.findAllRoom();
    }

    // 채팅방 번호로 채팅메세지를 다 가져온다
    @GetMapping("/{roomId}")
    public void findRoomByRoomId(@PathVariable long roomId, Model model) {
        log.info("GET /chat/findRoomByRoomId");
        log.info("roomId = {}", roomId);
        List<ChatMessageDto> chatMessageDtoList=chatService.findChMessageByRoomId(roomId);
        model.addAttribute("dto", chatMessageDtoList);
    }
}