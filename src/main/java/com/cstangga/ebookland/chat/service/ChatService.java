package com.cstangga.ebookland.chat.service;

import com.cstangga.ebookland.chat.dto.ChatMessageDto;
import com.cstangga.ebookland.chat.dto.ChatRoomDto;
import com.cstangga.ebookland.chat.entity.ChatMessage;
import com.cstangga.ebookland.chat.entity.ChatRoom;
import com.cstangga.ebookland.chat.repository.ChatRoomRepository;
import com.cstangga.ebookland.chat.repository.MessageRepository;
import com.cstangga.ebookland.member.controller.MemberController;
import com.cstangga.ebookland.member.entity.Member;
import com.cstangga.ebookland.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;
    private final SimpMessageSendingOperations messagingTemplate;


    // 관리자가 전체 채팅 방을 보기 위한 메소드
    public List<ChatRoomDto> findAllRoom() {
        List<ChatRoom> entity=chatRoomRepository.findAll();
        List<ChatRoomDto> chatRoomDtoList = new ArrayList<>();
        for(ChatRoom chatRoom:entity){
            chatRoomDtoList.add(chatRoom.toDto());
        }
        return chatRoomDtoList;
    }

    // 문의하기 버튼을 클릭하면, 채팅창이 열리고 -> db에서 전체 채팅 목록을 가져온다
    public List<ChatMessageDto> findChMessageByRoomId(long roomId) {
        log.info("service / findChMessageByRoomId");
        List<ChatMessage> chatMessageEntityList=messageRepository.findAllByChatRoomId(roomId);
        List<ChatMessageDto> chatMessageDtoList=new ArrayList<>();
        for(ChatMessage chatMessageEntity : chatMessageEntityList){
            chatMessageDtoList.add(chatMessageEntity.toDto());
        }
        return chatMessageDtoList;
    }

    // 회원가입 할 때 만들어진다
    public ChatRoom createRoom(Member member) {
        log.info("service /createRoom");

        Optional<Member> admin=memberRepository.findByUsername("a@a");
        ChatRoom entity=new ChatRoom(member,admin);

        entity=chatRoomRepository.save(entity);
        member=memberRepository.findMemberById(member.getId());
        member.setRoomId(entity.getId());
        memberRepository.save(member);
        log.info("저장 후 : ={}",entity);
        return entity;
    }

    public void sendMessage(ChatMessageDto dto) {
        log.info("service /sendMessage");
        // 이 부분에 DB에 저장
        // 채팅방을 구독한 -> 채팅방에 들어간 멤버
        messagingTemplate.convertAndSend("/sub/chat/room"+dto.getRoomId(),dto);

    }
}