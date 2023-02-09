package com.backend.util;

import com.backend.member.domain.MemberSession;

public class DomainTest {

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
