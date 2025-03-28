package com.cstangga.ebookland.recommend.entity;

import com.cstangga.ebookland.member.entity.Member;
import com.cstangga.ebookland.noticeboard.entity.Notice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RecommendType type; // LIKE, DISLIKE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noticeId")
    private Notice notice;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    public Recommend(RecommendType type, Member member, Notice notice){
        this.type=type;
        this.member=member;
        this.notice=notice;
    }

}
