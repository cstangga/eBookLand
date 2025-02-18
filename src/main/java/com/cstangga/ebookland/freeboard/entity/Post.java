package com.cstangga.ebookland.freeboard.entity;


import com.cstangga.ebookland.freeboard.dto.PostDto;
import com.cstangga.ebookland.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity(name = "post")
@Table(name = "tbl_post")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content",columnDefinition = "LONGTEXT")
    private String content;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Column(name = "views")
    @Builder.Default
    private int views=0;

    @Column(name = "likes")
    @Builder.Default
    private int likes=0;

    @Column(name = "dislikes")
    @Builder.Default
    private int dislikes=0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    public PostDto toDto() {

        return PostDto.builder()
                .member(member)
                .title(title)
                .postId(id)
                .content(content)
                .relativeTime(relativeTime())
                .member(member)
                .views(views)
                .likes(likes).build();
    }

    public void updatePost(PostDto postDto) {
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
        this.updateAt = LocalDateTime.now();
    }

    public String relativeTime() {
        Duration duration = Duration.between(createAt,LocalDateTime.now());

        long minutes = duration.toMinutes();
        long hours = duration.toHours();

        if (minutes < 1) {
            return "1분 미만";
        } else if (minutes < 60) {
            return minutes + "분 전";
        } else if (hours < 24) {
            return hours + "시간 전";
        } else {
            return createAt.format(DateTimeFormatter.ofPattern("MM-dd"));
        }
    }
}
