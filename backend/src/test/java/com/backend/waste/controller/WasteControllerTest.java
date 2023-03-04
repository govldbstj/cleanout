package com.backend.waste.controller;

import com.backend.member.domain.Member;
import com.backend.util.ControllerTest;
import com.backend.waste.domain.Waste;
import com.backend.waste.dto.request.PatchWaste;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class WasteControllerTest extends ControllerTest {

    @Test
    @DisplayName("이미지를 업로드하면 폐기물 등록에 성공합니다")
    void createWaste200() throws Exception {
        //given
        MultipartFile imageFile1 = new MockMultipartFile("image1", "waste1.PNG", MediaType.IMAGE_PNG_VALUE, "<<wasteImage1>>".getBytes());
        Member member = saveMemberInRepository();
        MockHttpSession session = loginMemberSession(member);

        //
        mockMvc.perform(
                        multipart("/waste-management/image")
                                .file("image", imageFile1.getBytes())
                                .session(session)
                                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andDo(document("waste/create/200"));
    }

    @Test
    @DisplayName("해당 멤버의 예약 조회가 성공합니다.")
    void get200() throws Exception {
        // given
        Member member = saveMemberInRepository();
        MockHttpSession session = loginMemberSession(member);
        saveWaste(member); // 등록완료
        Waste waste1 = saveWaste(member); // 예약완료
        reserveWaste(waste1.getId());
        Waste waste2 = saveWaste(member); // 수거완료
        collectWaste(waste2.getId());

        // expected
        mockMvc.perform(get("/waste-management/waste")
                        .session(session))
                .andExpect(status().isOk())
                .andDo(document("waste/get/200"));
    }

    @Test
    @DisplayName("세부 예약 내역을 확인합니다.")
    void getDetail200() throws Exception {
        // given
        Member member = saveMemberInRepository();
        MockHttpSession session = loginMemberSession(member);

        Waste waste1 = saveWaste(member); // 예약완료
        reserveWaste(waste1.getId());
        System.out.println();

        // expected
        mockMvc.perform(get("/waste-management/waste/{wasteIdx}",1)
                .session(session))
                .andExpect(status().isOk())
                .andDo(document("waste/getDetail/200"));
    }
}
