package com.cstangga.ebookland.member.dto;


import com.cstangga.ebookland.member.entity.MemberAddress;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateRequestDto {
    private String password;
    private String phoneNumber;
    private String email;
    private String name;
    private String address;
    private String postCode;
    private String extraAddress;

}
