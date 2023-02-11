package com.backend.member.controller;

import com.backend.member.domain.Member;
import com.backend.member.dto.request.MemberLogin;
import com.backend.member.dto.request.MemberSignup;
import com.backend.member.dto.request.MemberUpdate;
import com.backend.util.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
class MemberControllerTest extends ControllerTest {

    @Test
    @DisplayName("조건을 만족하면 회원가입에 성공합니다")
    void signup200() throws Exception {
        // given
        MemberSignup memberSignup = MemberSignup.builder()
                .email("xxx@gmail.com")
                .password("1234")
                .nickname("닉네임")
                .address("경기도 수원시 영통구")
                .phoneNumber("010-0000-0000")
                .build();

        String memberSignupJson = objectMapper.writeValueAsString(memberSignup);

        // expected
        mockMvc.perform(post("/signup")
                        .contentType(APPLICATION_JSON)
                        .content(memberSignupJson))
                .andExpect(status().isOk())
                .andDo(document("member/signup/200",
                        requestFields(
                                fieldWithPath("nickname").description("닉네임"),
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("password").description("비밀 번호"),
                                fieldWithPath("address").description("주소"),
                                fieldWithPath("phoneNumber").description("전화 번호"))
                        ));
    }

    @Test
    @DisplayName("이미 존재하는 회원이면 회원가입을 할 수 없습니다")
    void signup400() throws Exception {
        // given
        saveMemberInRepository();

        MemberSignup memberSignup = MemberSignup.builder()
                .email("xxx@gmail.com")
                .password("1234")
                .nickname("닉네임")
                .address("경기도 수원시 영통구")
                .phoneNumber("010-0000-0000")
                .build();

        String memberSignupJson = objectMapper.writeValueAsString(memberSignup);

        // expected
        mockMvc.perform(post("/signup")
                        .contentType(APPLICATION_JSON)
                        .content(memberSignupJson))
                .andExpect(status().isBadRequest())
                .andDo(document("member/signup/400",
                        requestFields(
                                fieldWithPath("nickname").description("존재하는 닉네임이거나"),
                                fieldWithPath("email").description("존재하는 이메일이다"),
                                fieldWithPath("password").description("비밀 번호"),
                                fieldWithPath("address").description("주소"),
                                fieldWithPath("phoneNumber").description("전화 번호")),
                        responseFields(
                                fieldWithPath("statusCode").description("에러 코드"),
                                fieldWithPath("message").description("에러 메세지")
                        )
                ));
    }

    @Test
    @DisplayName("가입된 회원이라면 로그인에 성공합니다")
    void login200() throws Exception {
        // given
        saveMemberInRepository();
        MemberLogin memberLogin = MemberLogin.builder()
                .email("xxx@gmail.com")
                .password("1234")
                .build();

        String memberLoginJson = objectMapper.writeValueAsString(memberLogin);

        // expected
        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON)
                        .content(memberLoginJson))
                .andExpect(status().isOk())
                .andDo(document("member/login/200",
                        requestFields(
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("password").description("비밀 번호")),
                        responseFields(
                                fieldWithPath("nickname").description("닉네임"),
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("password").description("비밀 번호"),
                                fieldWithPath("address").description("주소"),
                                fieldWithPath("phoneNumber").description("전화 번호"))
                ));
    }

    @Test
    @DisplayName("가입된 이메일이지만 비밀번호가 일치하지 않으면 예외가 발생합니다")
    void login400() throws Exception {
        // given
        saveMemberInRepository();
        MemberLogin memberLogin = MemberLogin.builder()
                .email("xxx@gmail.com")
                .password("3769392739")
                .build();

        String memberLoginJson = objectMapper.writeValueAsString(memberLogin);

        // expected
        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON)
                        .content(memberLoginJson))
                .andExpect(status().isBadRequest())
                .andDo(document("member/login/400",
                        requestFields(
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("password").description("일치하지 않는 비밀 번호")),
                        responseFields(
                                fieldWithPath("statusCode").description("에러 코드"),
                                fieldWithPath("message").description("에러 메세지"))
                ));
    }

    @Test
    @DisplayName("가입된 이메일이 아니라면 예외가 발생합니다")
    void login404() throws Exception {
        // given
        MemberLogin memberLogin = MemberLogin.builder()
                .email("가입되지 않은 이메일@gmail.com")
                .password("1234")
                .build();

        String memberLoginJson = objectMapper.writeValueAsString(memberLogin);

        // expected
        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON)
                        .content(memberLoginJson))
                .andExpect(status().isNotFound())
                .andDo(document("member/login/404",
                        requestFields(
                                fieldWithPath("email").description("가입되지 않은 이메일"),
                                fieldWithPath("password").description("비밀 번호")),
                        responseFields(
                                fieldWithPath("statusCode").description("에러 코드"),
                                fieldWithPath("message").description("에러 메세지"))
                ));
    }

    @Test
    @DisplayName("회원정보 수정에 성공합니다")
    void update200() throws Exception {
        // given
        Member member = saveMemberInRepository();
        MockHttpSession session = loginMemberSession(member);

        MemberUpdate memberUpdate = MemberUpdate.builder()
                .nickname("수정 닉네임")
                .email("update@gmail.com")
                .password("update1234")
                .address("경기도 서울시 강남구")
                .phoneNumber("010-1234-5678")
                .build();

        String memberUpdateJson = objectMapper.writeValueAsString(memberUpdate);

        // expected
        mockMvc.perform(patch("/member")
                        .session(session)
                        .contentType(APPLICATION_JSON)
                        .content(memberUpdateJson))
                .andExpect(status().isOk())
                .andDo(document("member/update/200",
                        requestFields(
                                fieldWithPath("nickname").description("수정 닉네임"),
                                fieldWithPath("email").description("수정 이메일"),
                                fieldWithPath("password").description("수정 비밀 번호"),
                                fieldWithPath("address").description("수정 주소"),
                                fieldWithPath("phoneNumber").description("수정 전화 번호")),
                        responseFields(
                                fieldWithPath("nickname").description("닉네임"),
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("password").description("비밀 번호"),
                                fieldWithPath("address").description("주소"),
                                fieldWithPath("phoneNumber").description("전화 번호"))
                ));
    }

    @Test
    @DisplayName("로그인하지 않으면 회원정보 수정에 실패합니다")
    void update401() throws Exception {
        // given
        Member member = saveMemberInRepository();

        MemberUpdate memberUpdate = MemberUpdate.builder()
                .nickname("수정 닉네임")
                .email("update@gmail.com")
                .password("update1234")
                .address("경기도 서울시 강남구")
                .phoneNumber("010-1234-5678")
                .build();

        String memberUpdateJson = objectMapper.writeValueAsString(memberUpdate);

        // expected
        mockMvc.perform(patch("/member")
                        .contentType(APPLICATION_JSON)
                        .content(memberUpdateJson))
                .andExpect(status().isUnauthorized())
                .andDo(document("member/update/401",
                        requestFields(
                                fieldWithPath("nickname").description("수정 닉네임"),
                                fieldWithPath("email").description("수정 이메일"),
                                fieldWithPath("password").description("수정 비밀 번호"),
                                fieldWithPath("address").description("수정 주소"),
                                fieldWithPath("phoneNumber").description("수정 전화 번호")),
                        responseFields(
                                fieldWithPath("statusCode").description("에러 코드"),
                                fieldWithPath("message").description("에러 메세지"))
                ));
    }

    @Test
    @DisplayName("회원 로그아웃을 진행하며 세션이 만료됩니다")
    void logout() throws Exception {
        // given
        Member member = saveMemberInRepository();
        MockHttpSession session = loginMemberSession(member);

        // expected
        mockMvc.perform(post("/logout")
                        .session(session))
                .andExpect(status().isOk())
                .andDo(document("member/logout/200"));
    }
}