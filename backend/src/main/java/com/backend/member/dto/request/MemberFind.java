package com.backend.member.dto.request;

import lombok.Getter;

@Getter
public class MemberFind {

    private String email;
    private String password;

    public MemberFind(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
