package com.backend.member.domain;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class MemberSession implements Serializable {

    private Long id;

    private String email;
    private String password;
    private String username;
    private String address;

    private String phoneNumber;

    @Builder
    public MemberSession(Long id, String email, String password, String username,
                         String address, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
