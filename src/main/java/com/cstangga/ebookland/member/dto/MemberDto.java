package com.cstangga.ebookland.member.dto;

import com.cstangga.ebookland.member.entity.Member;
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
    public MemberDto entityToDto(Member entity) {
        return MemberDto.builder()
                .memberId(entity.getId())
                .memberName(entity.getName())
                .address(entity.getAddress())
                .detailAddress(entity.getDetailAddress())
                .postCode(entity.getPostCode())
                .extraAddress(entity.getExtraAddress())
                .memberPhone(entity.getPhoneNumber())
                .build();
    }
}
