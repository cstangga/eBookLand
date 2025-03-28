package com.cstangga.ebookland.noticeboard.entity;

import com.cstangga.ebookland.noticeboard.dto.NoticeDto;
import com.cstangga.ebookland.recommend.entity.Recommend;
import com.cstangga.ebookland.recommend.entity.RecommendType;
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
import java.util.ArrayList;
import java.util.List;

@Entity(name = "notice")
@Table(name = "tbl_notice")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "contents",columnDefinition = "LONGTEXT")
    private String contents;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL)
    private List<Recommend> recommends = new ArrayList<>();

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Column(name = "views", columnDefinition = "BIGINT DEFAULT 0")
    @Builder.Default // 특정 값으로 초기화 하고 싶을 때 쓴다, 일일이 dto가 들어왔을 때 set하는 거 보다 만들어질 때 초기화 한다
    private long views=0;


    public NoticeDto toDto( ) {
        long likeCount = this.recommends.stream()
                .filter(type -> type.getType() == RecommendType.LIKES)
                .count();

        long dislikeCount = this.recommends.stream()
                .filter(r -> r.getType() == RecommendType.DISLIKES)
                .count();
        return new NoticeDto().builder()
                .noticeId(this.id)
                .contents(this.contents)
                .createAt(this.createAt)
                .views(this.views)
                .title(this.title)
                .likes(likeCount)
                .disLikes(dislikeCount)
                .relativeTime(relativeTime()).build();
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

    public void update(NoticeDto dto) {
        this.title=dto.getTitle();
        this.contents=dto.getContents();
        this.updateAt=LocalDateTime.now();
    }
}
