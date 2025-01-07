package com.cstangga.ebookland.member.entity;

import com.cstangga.ebookland.member.dto.ProfileUpdateRequestDto;
import com.cstangga.ebookland.member.dto.SignupDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tbl_member")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "postcode")
    private String postCode; // 우편번호

    @Column(name = "address")
    private String address; // 주소

    @Column(name = "detail_address")
    private String detailAddress; // 상세주소

    @Column(name = "extra_address")
    private String extraAddress; // 참고사항 ex) 아파트 정문에서 오른쪽으로 오면 빠릅니다

    @Column
    @Enumerated(EnumType.STRING) // enum을 string으로 관리
    private Gender gender;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "create_at",updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;


    // 회원 역할 - 최대 2개를 가질 수 있음.
    @Enumerated(EnumType.STRING) // enum을 string으로 관리
    @ElementCollection(fetch = FetchType.EAGER) // 컬렉션 객체임을 jpa가 알 수 있게 해줌, 지연 로딩
    @CollectionTable(
            name = "tbl_authority",
            joinColumns = @JoinColumn(name = "member_id") //
    )
    private Set<Authority> authorities = new HashSet<>();

    public void setDefaultAuthorities() {
        this.authorities = Set.of(Authority.ROLE_MEMBER);
    }

    public void updateProfile(ProfileUpdateRequestDto dto)
    {
        this.phoneNumber=dto.getPhoneNumber();
        this.password=dto.getPassword();
        this.email=dto.getEmail();
        this.name=dto.getName();
        this.postCode=dto.getPostCode();
        this.address=dto.getAddress();
        this.detailAddress=dto.getAddress();

    }

    public Member dtoToEntity(SignupDto dto)
    {
        return Member.builder()
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .name(dto.getName())
                .password(dto.getPassword())
                .address(dto.getAddress())
                .postCode(dto.getPostCode())
                .detailAddress(dto.getDetailAddress())
                .gender(dto.getGender())
                .extraAddress(dto.getExtraAddress())
                .birth(dto.getBirth())
                .build();
    }
}
