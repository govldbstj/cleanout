package com.backend.member.service;

import com.backend.member.domain.Member;
import com.backend.member.dto.request.MemberSignup;
import com.backend.member.exception.MemberDuplicationException;
import com.backend.util.ServiceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
                .username("회원 이름")
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
                .username("회원 이름")
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
                .username("회원 이름")
                .address("경기도 수원시 영통구")
                .phoneNumber("010-0000-0000")
                .build();

        // expected
        assertThrows(MemberDuplicationException.class,
                () -> memberService.validateDuplication(memberSignup));
    }
}