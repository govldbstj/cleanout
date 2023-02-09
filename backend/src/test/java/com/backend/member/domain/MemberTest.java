package com.backend.member.domain;

import com.backend.member.dto.request.MemberUpdate;
import com.backend.util.DomainTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.*;

public class MemberTest extends DomainTest {

    @Test
    @DisplayName("memberSession의 고유한 세션을 생성합니다")
    void makeSession() {
        // given
        MemberSession memberSession = getMemberSession();
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();

        // when
        memberSession.makeSession(httpServletRequest);

        // then
        HttpSession session = httpServletRequest.getSession(false);
        MemberSession findSession = (MemberSession) session.getAttribute("memberSession");
        assertThat(findSession).isNotNull();
    }

    @Test
    @DisplayName("회원 정보를 업데이트합니다")
    void update() {
        // given
        Member member = getMember();
        MemberUpdate memberUpdate = MemberUpdate.builder()
                .nickname("수정 닉네임")
                .email("update@gmail.com")
                .password("update1234")
                .address("경기도 서울시 강남구")
                .phoneNumber("010-1234-5678")
                .build();
        // when
        member.update(memberUpdate);

        // then
        assertThat(member.getNickname()).isEqualTo("수정 닉네임");
        assertThat(member.getEmail()).isEqualTo("update@gmail.com");
        assertThat(member.getPassword()).isEqualTo("update1234");
        assertThat(member.getAddress()).isEqualTo("경기도 서울시 강남구");
        assertThat(member.getPhoneNumber()).isEqualTo("010-1234-5678");
    }

    @Test
    @DisplayName("로그아웃을 하면 세션이 만료됩니다")
    void invalidateSession() {
        // given
        MemberSession memberSession = getMemberSession();
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        memberSession.makeSession(httpServletRequest);

        // when
        memberSession.invalidateSession(httpServletRequest);

        // then
        HttpSession session = httpServletRequest.getSession(false);
        assertThat(session).isNull();
    }
}
