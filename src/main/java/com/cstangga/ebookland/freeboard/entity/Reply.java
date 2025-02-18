package com.cstangga.ebookland.freeboard.entity;


import ch.qos.logback.core.joran.action.NOPAction;
import com.cstangga.ebookland.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "reply")
@Table(name = "tbl_reply")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "content", columnDefinition = "LONGTEXT")
    private String content;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // PostEntity 가 삭제되면 같이 삭제 됨
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private Comment Comment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // PostEntity 가 삭제되면 같이 삭제 됨
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;
}
