package com.cstangga.ebookland.bookboard.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "book_id")
    private String bookId;

    @Enumerated(EnumType.STRING) // enum을 string으로 관리, int로 관리하면 직관성이 떨어짐
    @ElementCollection(fetch = FetchType.EAGER) // 컬렉션 객체임을 jpa가 알 수 있게 해줌, 지연 로딩
    @CollectionTable(
            name = "tbl_purchase_status",
            joinColumns = @JoinColumn(name = "perchase_id")
            // 지금 entity @Id를 기본키로,tbl_purchase_status 테이블에서 외래키로 잡을거고 그 컬럼 이름을 "perchase_id" 이걸로 할 것이다
    )
    private Set<Purchase_status> purchaseStatuses =new HashSet<>();

}
