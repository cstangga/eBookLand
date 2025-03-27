package com.cstangga.ebookland.noticeboard.entity;

import com.cstangga.ebookland.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "recommend")
@Table(name = "tbl_recommend")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Recommend {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "likes")
    @Builder.Default
    private long likes =0;

    @Column(name = "disLikes")
    @Builder.Default
    private long disLikes=0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noticeId")
    private Notice notice;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

}
