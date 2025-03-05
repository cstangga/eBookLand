package com.cstangga.ebookland.admin.controller;

import com.cstangga.ebookland.chat.dto.ChatRoomDto;
import com.cstangga.ebookland.chat.entity.ChatRoom;
import com.cstangga.ebookland.chat.repository.ChatRoomRepository;
import com.cstangga.ebookland.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@org.springframework.stereotype.Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ChatService chatService;

    @GetMapping("/chatmanagement")
    public void adminBoard(Model model) {
        log.info("GET /admin/chatManagement");

        List<ChatRoomDto> chatRoomDtoList =chatService.findAllRoom();
        List<Long> roomIdList=new ArrayList<>();
        for(ChatRoomDto chatRoomDto:chatRoomDtoList){
            roomIdList.add(chatRoomDto.getRoomId());
        }

        model.addAttribute("roomIdList",roomIdList);
        model.addAttribute("chatRoomDtoList", chatRoomDtoList);
    }
}
