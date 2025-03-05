package com.cstangga.ebookland.chat.entity;


import com.cstangga.ebookland.chat.dto.ChatRoomDto;
import com.cstangga.ebookland.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity(name = "chat_room")
@Table(name = "tbl_chatroom")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "room_name")
    private String roomName; // 회원의 아이디가 들어가면 될 듯

    // 회원 : 채팅방 = 1:1
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE) // 멤버가 삭제되면 채팅방도 삭제 된다.
    @JoinColumn(name = "member_id", nullable = false, unique = true)
    private Member member;

    // 관리자 : 채팅 = N:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Member admin; // 상담 관리자

    @Column(name = "create_at")
    @CreationTimestamp // db에 들어갈 떄 생성됨
    private LocalDateTime createAt;

    public ChatRoom(Member member, Optional<Member> admin) {
        this.member = member;
        this.admin = admin.orElse(null);
        this.roomName = member.getNickName();

    }

    public ChatRoomDto toDto()
    {
        return ChatRoomDto.builder()
                .roomId(id)
                .admin(admin)
                .member(member)
                .roomName(roomName).build();
    }
}
