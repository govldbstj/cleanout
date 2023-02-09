package com.backend.member.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSignup {

    private String nickname;
    private String email;
    private String password;
    private String address;

    private String phoneNumber;

    @Builder
    public MemberSignup(String nickname, String email, String password, String address, String phoneNumber) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
