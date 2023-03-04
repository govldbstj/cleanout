package com.backend.waste.domain;

import com.backend.collector.domain.Collector;
import com.backend.member.domain.Member;
import com.backend.util.DomainTest;
import com.backend.waste.dto.request.PatchWaste;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class WasteTest extends DomainTest {

    @Test
    @DisplayName("폐기물을 등록합니다.")
    void makeWaste() {
        // given
        Member member = getMember();
        String imageName = UUID.randomUUID() + "image.png";

        // when
        Waste waste = Waste.createWaste(member, imageName);

        // then
        assertThat(waste).isNotNull();
        assertThat(waste.getMember()).isEqualTo(member);
        assertThat(waste.getImage()).isEqualTo(imageName);
    }

    @Test
    @DisplayName("ML서버를 통해 품명과 가격이 책정됩니다.")
    void updateWaste(){
        // given
        Waste waste = Waste.createWaste(getMember(),"WasteImage");
        PatchWaste patchWaste = new PatchWaste("냉장고-300L이상", 4000);

        // when
        String wasteName = patchWaste.getWasteName();
        int price = patchWaste.getPrice();
        waste.update(wasteName,price);

        // then
        assertThat(waste.getName()).isNotNull();
        assertThat(waste.getPrice()).isPositive();
    }

    @Test
    @DisplayName("수거자가 등록된 폐기물와 매칭됩니다.")
    void matchWithCollectot(){
        // given
        Waste waste = getWaste();
        Collector collector = getCollector();

        // when
        LocalDateTime timeToCollect = LocalDateTime
                .parse("2023-03-15 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        waste.matchCollector(collector, timeToCollect);

        // then
        assertThat(waste.getCollector()).isEqualTo(collector);
        assertThat(waste.getCollectedDate()).isNotNull();
    }

    @Test
    @DisplayName("수거자가 폐기물을 수거합니다.")
    void collected(){
        // given
        Waste waste = getWaste();
        Collector collector = getCollector();

        // when
        waste.collected(collector);

        // then
        assertThat(waste.isCollected()).isTrue();
        assertThat(waste.getCollectedDate().getDayOfMonth()).isEqualTo(LocalDateTime.now().getDayOfMonth());
        assertThat(waste.getCollector()).isNotNull();
    }
}
