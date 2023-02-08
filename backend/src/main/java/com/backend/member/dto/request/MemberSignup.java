package com.backend.member.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSignup {

    private String email;
    private String password;
    private String username;
    private String address;

    private String phoneNumber;

    @Builder
    public MemberSignup(String email, String password, String username, String address, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
