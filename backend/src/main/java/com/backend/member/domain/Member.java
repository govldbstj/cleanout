package com.backend.member.domain;

import com.backend.member.dto.request.MemberSignup;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private String address;

    private String phoneNumber;

    @Builder
    public Member(String email, String password, String nickname, String address, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public static Member getFromMemberSignup(MemberSignup memberSignup) {
        return Member.builder()
                .email(memberSignup.getEmail())
                .password(memberSignup.getPassword())
                .nickname(memberSignup.getNickname())
                .address(memberSignup.getAddress())
                .phoneNumber(memberSignup.getPhoneNumber())
                .build();
    }
}
