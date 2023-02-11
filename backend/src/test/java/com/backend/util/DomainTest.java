package com.backend.util;

import com.backend.member.domain.Member;
import com.backend.member.domain.MemberSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DomainTest {

    protected PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    protected Member getMember() {
        Member member = Member.builder()
                .nickname("닉네임")
                .email("xxx@gmail.com")
                .password("1234")
                .address("경기도 수원시 영통구")
                .phoneNumber("010-0000-0000")
                .build();
        return member;
    }

    protected MemberSession getMemberSession() {
        MemberSession memberSession = MemberSession.builder()
                .id(1L)
                .nickname("닉네임")
                .email("xxx@gmail.com")
                .password("1234")
                .address("경기도 수원시 영통구")
                .phoneNumber("010-0000-0000")
                .build();

        return memberSession;
    }
}
