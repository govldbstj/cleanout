package com.backend.member.domain;

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
}
