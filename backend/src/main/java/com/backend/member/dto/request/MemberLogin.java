package com.backend.member.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberLogin {

    private String email;
    private String password;

    @Builder
    public MemberLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
