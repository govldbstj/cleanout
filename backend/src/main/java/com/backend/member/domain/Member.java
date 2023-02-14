package com.backend.member.domain;

import com.backend.kakao.dto.KakaoSignup;
import com.backend.member.dto.request.MemberSignup;
import com.backend.util.BaseTimeEntity;
import com.backend.waste.domain.Waste;
import com.backend.member.dto.request.MemberUpdate;
import com.backend.util.enumerated.SignupType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.*;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static org.apache.logging.log4j.util.Strings.EMPTY;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private String address;

    private String phoneNumber;

    @OneToMany(mappedBy = "member")
    private List<Waste> wastes = new ArrayList<>();

    @Enumerated(STRING)
    private SignupType signupType;

    private String accessToken;

    @Builder
    public Member(String email, String password, String nickname, String address,
                  String phoneNumber, SignupType signupType, String accessToken) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.signupType = signupType;
        this.accessToken = accessToken;
    }

    public static Member getFromMemberSignup(MemberSignup memberSignup) {
        return Member.builder()
                .email(memberSignup.getEmail())
                .password(memberSignup.getPassword())
                .nickname(memberSignup.getNickname())
                .address(memberSignup.getAddress())
                .phoneNumber(memberSignup.getPhoneNumber())
                .signupType(SignupType.NORMAL)
                .accessToken(EMPTY)
                .build();
    }

    public static Member getFromKakaoSignup(KakaoSignup kakaoSignup) {
        return Member.builder()
                .email(kakaoSignup.getEmail())
                .nickname(kakaoSignup.getNickname())
                .accessToken(kakaoSignup.getAccessToken())
                .password(EMPTY)
                .address(EMPTY)
                .phoneNumber(EMPTY)
                .signupType(SignupType.KAKAO)
                .build();
    }

    public void update(MemberUpdate memberUpdate, PasswordEncoder passwordEncoder) {
        this.email = memberUpdate.getEmail();
        this.password = passwordEncoder.encode(memberUpdate.getPassword());
        this.nickname = memberUpdate.getNickname();
        this.address = memberUpdate.getAddress();
        this.phoneNumber = memberUpdate.getPhoneNumber();
    }

    public void updateAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
