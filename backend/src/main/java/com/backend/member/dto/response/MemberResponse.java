package com.backend.member.dto.response;

import com.backend.member.domain.MemberSession;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

    private String username;
    private String email;
    private String password;
    private String address;

    private String phoneNumber;

    @Builder
    public MemberResponse(String username, String email, String password, String address, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public static MemberResponse getFromMemberSession(MemberSession memberSession) {
        return MemberResponse.builder()
                .username(memberSession.getUsername())
                .email(memberSession.getEmail())
                .password(memberSession.getPassword())
                .address(memberSession.getAddress())
                .phoneNumber(memberSession.getPhoneNumber())
                .build();
    }
}
