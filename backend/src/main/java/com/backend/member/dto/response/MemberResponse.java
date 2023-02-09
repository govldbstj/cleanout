package com.backend.member.dto.response;

import com.backend.member.domain.Member;
import com.backend.member.domain.MemberSession;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

    private String nickname;
    private String email;
    private String password;
    private String address;

    private String phoneNumber;

    @Builder
    public MemberResponse(String nickname, String email, String password, String address, String phoneNumber) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public static MemberResponse getFromMember(Member member) {
        return MemberResponse.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .password(member.getPassword())
                .address(member.getAddress())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    public static MemberResponse getFromMemberSession(MemberSession memberSession) {
        return MemberResponse.builder()
                .nickname(memberSession.getNickname())
                .email(memberSession.getEmail())
                .password(memberSession.getPassword())
                .address(memberSession.getAddress())
                .phoneNumber(memberSession.getPhoneNumber())
                .build();
    }
}
