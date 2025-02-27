package com.cstangga.ebookland.member.dto;

import com.cstangga.ebookland.member.entity.Gender;
import com.cstangga.ebookland.member.entity.Member;
import com.cstangga.ebookland.member.entity.MemberAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupDto {

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private Gender gender;
    private String postCode;
    private String address;
    private String detailAddress;
    private String extraAddress;
    private LocalDate birth;
    private String nickName;
    private long roomId;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .phoneNumber(phoneNumber)
                .name(name)
                .nickName(nickName)
                .password(password)
                .address(address)
                .postCode(postCode)
                .detailAddress(detailAddress)
                .gender(gender)
                .extraAddress(extraAddress)
                .birth(birth)
                .roomId(roomId)
                .build();
    }
}
