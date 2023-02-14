package com.backend.util;

import com.backend.member.domain.Member;
import com.backend.member.dto.request.MemberLogin;
import com.backend.member.repository.MemberRepository;
import com.backend.member.service.MemberService;
import com.backend.waste.domain.Waste;
import com.backend.waste.repository.WasteRepository;
import com.backend.waste.service.WasteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
public class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected MemberService memberService;

    @Autowired
    protected WasteRepository wasteRepository;

    @Autowired
    protected WasteService wasteService;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint())
                        .and()
                        .uris()
                        .withScheme("http")
                        .withHost("43.200.115.73")
                        .withPort(8080))
                .build();
    }

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

    protected MockHttpSession loginMemberSession(Member member) throws Exception {
        MemberLogin memberLogin = MemberLogin.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .build();

        String loginJson = objectMapper.writeValueAsString(memberLogin);

        MockHttpServletRequest request = mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON)
                        .content(loginJson))
                .andReturn().getRequest();

        HttpSession session = request.getSession();
        return (MockHttpSession) session;
    }

    protected Waste saveWaste(Member member) throws IOException {
        MultipartFile imageFile = new MockMultipartFile("image","waste1.PNG", MediaType.IMAGE_PNG_VALUE,"<<wasteImage>>".getBytes());
        return wasteService.postWasteImage(member.getId(),imageFile);
    }
}
