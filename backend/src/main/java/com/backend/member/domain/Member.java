package com.backend.member.domain;

import com.backend.kakao.dto.KakaoSignup;
import com.backend.member.dto.request.MemberSignup;
import com.backend.member.dto.request.MemberUpdate;
import com.backend.util.enumerated.SignupType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.*;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static org.apache.logging.log4j.util.Strings.EMPTY;

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

    @Enumerated(STRING)
    private SignupType signupType;

    @Builder
    public Member(String email, String password, String nickname,
                  String address, String phoneNumber, SignupType signupType) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.signupType = signupType;
    }

    public static Member getFromMemberSignup(MemberSignup memberSignup) {
        return Member.builder()
                .email(memberSignup.getEmail())
                .password(memberSignup.getPassword())
                .nickname(memberSignup.getNickname())
                .address(memberSignup.getAddress())
                .phoneNumber(memberSignup.getPhoneNumber())
                .signupType(SignupType.NORMAL)
                .build();
    }

    public static Member getFromKakaoSignup(KakaoSignup kakaoSignup) {
        return Member.builder()
                .email(kakaoSignup.getEmail())
                .password(EMPTY)
                .nickname(kakaoSignup.getNickname())
                .address(EMPTY)
                .phoneNumber(EMPTY)
                .signupType(SignupType.KAKAO)
                .build();
    }

    public void update(MemberUpdate memberUpdate) {
        this.email = memberUpdate.getEmail();
        this.password = memberUpdate.getPassword();
        this.nickname = memberUpdate.getNickname();
        this.address = memberUpdate.getAddress();
        this.phoneNumber = memberUpdate.getPhoneNumber();
    }
}
