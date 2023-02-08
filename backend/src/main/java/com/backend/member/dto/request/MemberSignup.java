package com.backend.member.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSignup {

    private String username;
    private String email;
    private String password;
    private String address;

    private String phoneNumber;

    @Builder
    public MemberSignup(String username, String email, String password, String address, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
