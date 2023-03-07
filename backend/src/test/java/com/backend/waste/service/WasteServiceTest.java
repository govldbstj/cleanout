package com.backend.waste.service;

import com.backend.collector.service.CollectorService;
import com.backend.member.domain.Member;
import com.backend.util.ServiceTest;
import com.backend.waste.domain.Waste;
import com.backend.waste.dto.request.PatchWaste;
import com.backend.waste.dto.response.GetWasteBrief;
import com.backend.waste.dto.response.GetWasteDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class WasteServiceTest extends ServiceTest {

    @Autowired
    private WasteService wasteService;
    @Autowired
    private CollectorService collectorService;

    @Test
    @DisplayName("업로드된 이미지로 폐기물이 등록됩니다")
    void upload() throws IOException {
        //given
        Member member = saveMemberInRepository();
        MultipartFile imageFile = new MockMultipartFile("wasteImage", "wasteImage.PNG", MediaType.IMAGE_PNG_VALUE, "<<<waste>>>".getBytes());


        //when
        Waste waste = wasteService.createWaste(member.getId(), imageFile);

        //then
        assertThat(waste).isNotNull();
        assertThat(waste.getName()).isNotNull();
        assertThat(waste.getPrice()).isPositive();
        assertThat(member.getWastes()).isNotNull();
    }

    @Test
    @DisplayName("해당 멤버의 등록된 폐기물을 조회합니다.")
    void getWastes() throws Exception {
        // given
        Member member = saveMemberInRepository();
        Waste waste = getWaste(member);

        // when
        List<GetWasteBrief> getWasteBriefs = wasteService.getWasteList(waste.getId());

        //then
        assertThat(getWasteBriefs).isNotNull();
        assertThat(getWasteBriefs.size()).isEqualTo(1);
        assertThat(getWasteBriefs.get(0).getId()).isEqualTo(1);
        assertThat(getWasteBriefs.get(0).getDate().getDayOfMonth()).isEqualTo(LocalDateTime.now().getDayOfMonth());
    }

    @Test
    @DisplayName("등록된 폐기물을 상세조회합니다.")
    void getWasteDetail() throws Exception {
        //given
        Member member = saveMemberInRepository();
        Waste waste = getWaste(member);

        //when
        GetWasteDetail getWasteDetail = wasteService.getWaste(member.getId(), waste.getId());

        assertThat(getWasteDetail).isNotNull();
        assertThat(getWasteDetail.getAddress()).isNotNull();
    }

    @Test
    @DisplayName("ML 서버로부터 폐기물의 품명과 책정된 가격을 응답받습니다.")
    void getWasteNameAndPrice() throws Exception {
        // given
        MultipartFile imageFile = new MockMultipartFile("wasteImage", "wasteImage.PNG", MediaType.IMAGE_PNG_VALUE, "<<<waste>>>".getBytes());

        // when
        PatchWaste patchWaste = wasteService.requestToML(imageFile);

        // then
        assertThat(patchWaste.getWasteName()).isNotNull();
        assertThat(patchWaste.getPrice()).isPositive();
    }

    @Test
    @DisplayName("예약된 폐기물의 경우 상태표시가 현재로부터 수거예정 시점까지의 시간이 됩니다.")
    void statusIsReserved() throws Exception {
        // given
        Member member = saveMemberInRepository();
        Waste waste = getWaste(member);
        collectorService.matchCollector(waste.getId(), getCollector(waste).getId(), "2023-03-05 00:50:00");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = waste.getCollectedDate();
        int timeDiff = time.getMinute() - now.getMinute();
        // ***주의*** 현재시간과 예약된 시간 차이가 1시간 이내여야 테스트 코드가 성공합니다. 또한 1분 빼줍니다.

        // when
        String status = wasteService.getTimeGap(waste);

        // then
        assertThat(status.substring(status.length() - 8)).isEqualTo(timeDiff - 1 + "분 후 수거");
    }

    @Test
    @DisplayName("수거된 폐기물의 경우 상태표시가 '수거 완료'가 됩니다.")
    void statusIsCollected() throws Exception {
        // given
        Member member = saveMemberInRepository();
        Waste waste = getWaste(member);
        collectorService.collectWaste(waste.getId(), getCollector(waste).getId());

        // when
        String status = wasteService.getTimeGap(waste);

        // then
        assertThat(status).isEqualTo("수거 완료");
    }

    @Test
    @DisplayName("등록된 폐기물의 경우 상태표시가 '등록 완료'가 됩니다.")
    void statusIsEnrolled() throws Exception {
        // given
        Member member = saveMemberInRepository();
        Waste waste = getWaste(member);

        // when
        String status = wasteService.getTimeGap(waste);

        // then
        assertThat(status).isEqualTo("등록 완료");
    }
}
