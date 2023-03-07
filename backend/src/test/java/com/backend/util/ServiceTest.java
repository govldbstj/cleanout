package com.backend.util;

import com.backend.collector.domain.Collector;
import com.backend.collector.dto.request.PostCollector;
import com.backend.collector.service.CollectorService;
import com.backend.member.domain.Member;
import com.backend.member.domain.MemberSession;
import com.backend.member.dto.request.MemberSignup;
import com.backend.member.repository.MemberRepository;
import com.backend.waste.domain.Waste;
import com.backend.waste.repository.WasteRepository;
import com.backend.waste.service.WasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SpringBootTest
public class ServiceTest {

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected WasteService wasteService;
    @Autowired
    protected CollectorService collectorService;

    @Autowired
    protected WasteRepository wasteRepository;
    protected PasswordEncoder passwordEncoder;

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

    protected MemberSession getMemberSessionUtil() {
        MemberSession memberSession = MemberSession.builder()
                .id(1L)
                .nickname("닉네임")
                .email("xxx@gmail.com")
                .password("1234")
                .address("경기도 수원시 영통구")
                .phoneNumber("010-0000-0000")
                .build();

        return memberSession;
    }

    protected Waste getWaste(Member member) throws IOException {
        MultipartFile imageFile = new MockMultipartFile("wasteImage","wasteImage.PNG", MediaType.IMAGE_PNG_VALUE,"<<<waste>>>".getBytes());
        Waste waste = wasteService.createWaste(member.getId(),imageFile);

        return waste;
    }

    protected Collector getCollector(Waste waste){
        Member member = saveMemberInRepository();
        Collector collector = collectorService.createCollector(new PostCollector("수거자1","010-1111-1111"));

        return collector;
    }
}
