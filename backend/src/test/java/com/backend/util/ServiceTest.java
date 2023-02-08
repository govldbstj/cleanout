package com.backend.util;

import com.backend.member.domain.Member;
import com.backend.member.domain.MemberSession;
import com.backend.member.dto.request.MemberSignup;
import com.backend.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {

    @Autowired
    protected MemberRepository memberRepository;

    protected Member saveMemberInRepository() {
        Member member = Member.builder()
                .email("yhwjd99@gmail.com")
                .password("1234")
                .username("회원 이름")
                .address("경기도 수원시 영통구")
                .phoneNumber("010-0000-0000")
                .build();

        return memberRepository.save(member);
    }
}
