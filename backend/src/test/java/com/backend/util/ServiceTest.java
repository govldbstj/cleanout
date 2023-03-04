package com.backend.util;

import com.backend.member.domain.Member;
import com.backend.member.domain.MemberSession;
import com.backend.member.dto.request.MemberSignup;
import com.backend.member.repository.MemberRepository;
import com.backend.waste.repository.WasteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class ServiceTest {

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected WasteRepository wasteRepository;
    protected PasswordEncoder passwordEncoder;

    protected Member saveMemberInRepository() {
        Member member = Member.builder()
                .email("xxx@gmail.com")
                .password("1234")
                .nickname("닉네임")
                .address("경기도 수원시 영통구")
                .phoneNumber("010-0000-0000")
                .build();

        return memberRepository.save(member);
    }

    protected MemberSession getMemberSessionUtil() {
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
