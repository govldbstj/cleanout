package com.backend.member.service;

import com.backend.kakao.dto.KakaoLogin;
import com.backend.kakao.service.KakaoService;
import com.backend.member.domain.Member;
import com.backend.member.domain.MemberSession;
import com.backend.member.dto.request.MemberLogin;
import com.backend.member.dto.request.MemberSignup;
import com.backend.member.dto.request.MemberUpdate;
import com.backend.member.exception.MemberDuplicationException;
import com.backend.util.DomainTest;
import com.backend.util.ServiceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
class MemberServiceTest extends ServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private KakaoService kakaoService;

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
                .email("xxx@gmail.com")
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
                .email("xxx@gmail.com")
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

    @Test
    @DisplayName("회원 정보를 수정합니다")
    void update() {
        // given
        Member member = saveMemberInRepository();
        MemberSession memberSession = MemberSession.getFromMember(member);

        MemberUpdate memberUpdate = MemberUpdate.builder()
                .nickname("수정 닉네임")
                .email("update@gmail.com")
                .password("update1234")
                .address("경기도 서울시 강남구")
                .phoneNumber("010-1234-5678")
                .build();

        // when
        memberService.update(memberSession, memberUpdate);

        // then
        assertThat(member.getNickname()).isEqualTo("수정 닉네임");
        assertThat(member.getEmail()).isEqualTo("update@gmail.com");
        assertThat(member.getAddress()).isEqualTo("경기도 서울시 강남구");
        assertThat(member.getPhoneNumber()).isEqualTo("010-1234-5678");
//        assertThat(passwordEncoder.matches("update1234", member.getPassword())).isTrue();
    }

    @Test
    @DisplayName("카카오로 로그인한 회원이 우리 서비스에 가입되지 않은 회원이면 true를 반환합니다")
    void needToSignupTrue() {
        // given
        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("nickname", "닉네임");
        userInfo.put("email", "xxx@gmail.com");

        // when
        boolean needToSignup = memberService.needToSignup(userInfo);

        // then
        assertThat(needToSignup).isEqualTo(true);
    }

    @Test
    @DisplayName("카카오로 로그인한 회원이 우리 서비스에 가입된 회원이면 false를 반환합니다")
    void needToSignupFalse() {
        // given
        saveMemberInRepository();
        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("nickname", "닉네임");
        userInfo.put("email", "xxx@gmail.com");

        // when
        boolean needToSignup = memberService.needToSignup(userInfo);

        // then
        assertThat(needToSignup).isEqualTo(false);
    }

    @Test
    @DisplayName("카카오 로그인 정보로부터 가입된 회원의 정보를 가져옵니다")
    void getByKakaoLogin() {
        // given
        saveMemberInRepository();

        KakaoLogin kakaoLogin = KakaoLogin.builder()
                .nickname("닉네임")
                .email("xxx@gmail.com")
                .build();
        // when
        Member getByKaKaoLogin = kakaoService.getByKakaoLogin(kakaoLogin);

        // then
        assertThat(getByKaKaoLogin.getNickname()).isEqualTo("닉네임");
        assertThat(getByKaKaoLogin.getEmail()).isEqualTo("xxx@gmail.com");
    }

    @Test
    @DisplayName("로그아웃을 하면 세션이 만료됩니다")
    void logout() {
        // given
        MemberSession memberSession = getMemberSessionUtil();
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        memberSession.makeSession(httpServletRequest);

        // when
        memberService.logout(memberSession, httpServletRequest);

        // then
        HttpSession session = httpServletRequest.getSession(false);
        assertThat(session).isNull();
    }
}