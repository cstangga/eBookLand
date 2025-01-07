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

    private SignupDto entityToSignUpDto(Member entity) {
        return SignupDto.builder()
                .email(entity.getEmail())
                .password(entity.getPassword())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .postCode(entity.getPostCode())
                .address(entity.getAddress())
                .detailAddress(entity.getDetailAddress())
                .extraAddress(entity.getExtraAddress())
                .birth(entity.getBirth())
                .gender(entity.getGender()).build();
    }
}
