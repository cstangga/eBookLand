package com.cstangga.ebookland.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_purchaseinfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseInfo {

    @Id
    private long id;

    @Column(name = "bookid")
    private long bookId;

    @Column(name = "memberid")
    private long memberId;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "partner_order_id")
    private String partnerOrderId; // 결제 고유 번호
}
