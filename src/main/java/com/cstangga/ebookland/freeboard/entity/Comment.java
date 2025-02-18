package com.cstangga.ebookland.freeboard.entity;


import com.cstangga.ebookland.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "comment")
@Table(name = "tbl_comment")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "content" ,columnDefinition = "LONGTEXT")
    private String content;


    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // PostEntity 가 삭제되면 같이 삭제 됨
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // PostEntity 가 삭제되면 같이 삭제 됨
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;


}
