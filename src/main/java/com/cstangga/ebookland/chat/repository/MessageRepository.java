package com.cstangga.ebookland.chat.repository;

import com.cstangga.ebookland.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {
    // 채팅메세지를 가져올 때 채팅방까지 가져온다
    @Query(value = "select cm from chat_message cm join fetch  cm.chatRoom where cm.chatRoom.id=:chatRoomId")
    List<ChatMessage> findAllByChatRoomId(long chatRoomId);
}
