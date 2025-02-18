package com.cstangga.ebookland.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private long memberId;
    private String memberName;
    private String memberPhone;
    private String address;
    private String postCode;
    private String detailAddress;
    private String extraAddress;
    private String nickName;
    public MemberDto dtoToEntity() {
        return MemberDto.builder()
                .memberId(memberId)
                .memberName(memberName)
                .address(address)
                .detailAddress(detailAddress)
                .postCode(postCode)
                .extraAddress(extraAddress)
                .memberPhone(memberPhone)
                .nickName(nickName)
                .build();
    }
}
