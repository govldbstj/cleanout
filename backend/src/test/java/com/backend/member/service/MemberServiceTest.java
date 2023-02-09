package com.backend.member.service;

import com.backend.member.domain.Member;
import com.backend.member.domain.MemberSession;
import com.backend.member.dto.request.MemberLogin;
import com.backend.member.dto.request.MemberSignup;
import com.backend.member.exception.MemberDuplicationException;
import com.backend.util.ServiceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
class MemberServiceTest extends ServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("입력한 정보로 회원가입이 진행됩니다")
    void signup() {
        // given
        MemberSignup memberSignup = MemberSignup.builder()
                .email("yhwjd99@gmail.com")
                .password("1234")
                .nickname("닉네임")
                .address("경기도 수원시 영통구")
                .phoneNumber("010-0000-0000")
                .build();

        // when
        memberService.signup(memberSignup);

        // then
        assertThat(memberRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("존재하지 않는 회원이라면 메소드를 통과합니다")
    void validateDuplication200() {
        // given
        MemberSignup memberSignup = MemberSignup.builder()
                .email("yhwjd99@gmail.com")
                .password("1234")
                .nickname("닉네임")
                .address("경기도 수원시 영통구")
                .phoneNumber("010-0000-0000")
                .build();

        // expected
        memberService.validateDuplication(memberSignup);
    }

    @Test
    @DisplayName("이미 존재하는 회원이라면 예외가 발생합니다")
    void validateDuplication400() {
        // given
        saveMemberInRepository();
        MemberSignup memberSignup = MemberSignup.builder()
                .email("yhwjd99@gmail.com")
                .password("1234")
                .nickname("닉네임")
                .address("경기도 수원시 영통구")
                .phoneNumber("010-0000-0000")
                .build();

        // expected
        assertThrows(MemberDuplicationException.class,
                () -> memberService.validateDuplication(memberSignup));
    }

    @Test
    @DisplayName("로그인 정보로부터 세션을 만들기 위한 객체인 memberSession을 가져옵니다")
    void getMemberSession() {
        // given
        Member member = saveMemberInRepository();
        MemberLogin memberLogin = MemberLogin.builder()
                .email("yhwjd99@gmail.com")
                .password("1234")
                .build();

        // when
        MemberSession memberSession = memberService.getMemberSession(memberLogin);

        // then
        assertThat(memberSession.getId()).isEqualTo(member.getId());
    }

    @Test
    @DisplayName("memberSession 객체의 고유한 세션을 생성합니다")
    void makeSessionForMemberSession() {
        // given
        MemberSession memberSession = MemberSession.builder()
                .id(1L)
                .nickname("닉네임")
                .email("xxx@gmail.com")
                .password("1234")
                .address("경기도 수원시 영통구")
                .phoneNumber("010-0000-0000")
                .build();

        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();

        // when
        memberService.makeSessionForMemberSession(memberSession, httpServletRequest);

        // then
        HttpSession session = httpServletRequest.getSession(false);
        MemberSession findMemberSession = (MemberSession) session.getAttribute("memberSession");
        assertThat(findMemberSession).isNotNull();
    }
}