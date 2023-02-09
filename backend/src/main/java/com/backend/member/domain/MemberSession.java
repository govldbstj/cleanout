package com.backend.member.domain;

import com.backend.kakao.dto.KakaoLogin;
import com.backend.util.enumerated.SignupType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

import static javax.persistence.EnumType.STRING;

@Getter
public class MemberSession implements Serializable {

    private Long id;
    private String nickname;

    private String email;
    private String password;
    private String address;

    private String phoneNumber;
    @Enumerated(STRING)
    private SignupType signupType;

    @Builder
    public MemberSession(Long id, String email, String password, String nickname,
                         String address, String phoneNumber, SignupType signupType) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.signupType = signupType;
    }

    public static MemberSession getFromMember(Member member) {
        return MemberSession.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .password(member.getPassword())
                .address(member.getAddress())
                .phoneNumber(member.getPhoneNumber())
                .signupType(member.getSignupType())
                .build();
    }

    public void makeSession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("memberSession", this);
    }

    public void invalidateSession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        session.invalidate();
    }
}
