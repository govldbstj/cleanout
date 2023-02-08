package com.backend.member.controller;

import com.backend.member.domain.Member;
import com.backend.member.dto.request.MemberSignup;
import com.backend.util.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
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
                .username("회원 이름")
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
                                fieldWithPath("username").description("회원 이름"),
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
                .username("회원 이름")
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
                                        fieldWithPath("username").description("회원 이름"),
                                        fieldWithPath("email").description("이메일"),
                                        fieldWithPath("password").description("비밀 번호"),
                                        fieldWithPath("address").description("주소"),
                                        fieldWithPath("phoneNumber").description("전화 번호"))
                                ));
    }
}