package com.backend.member.domain;

import lombok.Builder;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Getter
public class MemberSession implements Serializable {

    private Long id;
    private String username;

    private String email;
    private String password;
    private String address;

    private String phoneNumber;

    @Builder
    public MemberSession(Long id, String email, String password, String username,
                         String address, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public static MemberSession getFromMember(Member member) {
        return MemberSession.builder()
                .id(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .password(member.getPassword())
                .address(member.getAddress())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    public void makeSession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("memberSession", this);
    }
}
