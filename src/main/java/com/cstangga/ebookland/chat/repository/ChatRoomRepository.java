package com.cstangga.ebookland.chat.repository;

import com.cstangga.ebookland.chat.dto.ChatRoomDto;
import com.cstangga.ebookland.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query
    ChatRoom findChatRoomById(long roomId);
}
