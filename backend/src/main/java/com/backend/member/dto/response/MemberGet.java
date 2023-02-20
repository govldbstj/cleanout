package com.backend.member.dto.response;

import com.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberGet {

    private String nickname;
    private String address;

    @Builder
    public MemberGet(String nickname, String address) {
        this.nickname = nickname;
        this.address = address;
    }

    public static MemberGet getFromMember(Member member) {
        return MemberGet.builder()
                .nickname(member.getNickname())
                .address(member.getAddress())
                .build();
    }
}
