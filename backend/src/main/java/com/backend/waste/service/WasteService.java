package com.backend.waste.service;


import com.backend.member.domain.Member;
import com.backend.member.repository.MemberRepository;
import com.backend.waste.domain.Waste;
import com.backend.waste.dto.request.PatchWaste;
import com.backend.waste.dto.response.GetWasteBrief;
import com.backend.waste.dto.response.GetWasteDetail;
import com.backend.waste.repository.WasteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class WasteService {

    private final MemberRepository memberRepository;
    private final WasteRepository wasteRepository;


    @Value("${spring.file.path}")
    private String uploadFolder;

    @Transactional
    public Waste postWasteImage(Long memberIdx, MultipartFile file) throws IOException {
        Member member = memberRepository.getById(memberIdx);
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + file.getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder + "/" + imageFileName);
        Files.write(imageFilePath, file.getBytes());
        Waste waste = Waste.createWaste(member, imageFileName);
        wasteRepository.save(waste);
        return waste;
    }

    @Transactional
    public void updateWaste(PatchWaste patchWaste) {
        Waste waste = wasteRepository.getByImageName(patchWaste.getImageName());
        waste.update(patchWaste.getWasteName(), patchWaste.getPrice());
    }

    @Transactional
    public List<GetWasteBrief> getWasteList(Long id) {
        Member member = memberRepository.getById(id);
        List<Waste> wastes = wasteRepository.getWasteList(member);

        List<GetWasteBrief> result = new ArrayList<>();
        for (Waste waste : wastes) {
            result.add(new GetWasteBrief(waste.getId(), waste.getName(), waste.getEnrolledDate(), getTimeGap(waste)));
        }
        return result;
    }

    String getTimeGap(Waste waste) {
        StringBuilder sb = new StringBuilder();
        if (waste.isCollected())
            sb.append("수거 완료");
        else if (waste.getCollector() == null)
            sb.append("등록 완료");
        else {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime time = waste.getCollectedDate();
            if (ChronoUnit.YEARS.between(now, time) != 0) {
                long yearGap = ChronoUnit.YEARS.between(now, time);
                sb.append(yearGap).append("년 ");
                time = time.minusYears(yearGap);
            }
            if (ChronoUnit.MONTHS.between(now, time) != 0) {
                long monthGap = ChronoUnit.MONTHS.between(now, time);
                sb.append(monthGap).append("달 ");
                time = time.minusMonths(monthGap);
            }
            if (ChronoUnit.DAYS.between(now, time) != 0) {
                long dayGap = ChronoUnit.DAYS.between(now, time);
                sb.append(dayGap).append("일 ");
                time = time.minusDays(dayGap);
            }
            if (ChronoUnit.HOURS.between(now, time) != 0) {
                long hourGap = ChronoUnit.HOURS.between(now, time);
                sb.append(hourGap).append("시간 ");
                time = time.minusHours(hourGap);
            }
            sb.append(ChronoUnit.MINUTES.between(now, time)).append("분 후 수거");
        }

        return sb.toString();
    }

    public GetWasteDetail getWaste(Long userIdx, Long wasteIdx) throws IOException {
        Member member = memberRepository.getById(userIdx);
        Waste waste = wasteRepository.getById(wasteIdx);

        byte[] imageByteArray = null;

        if (waste.getImageName() != null) {
            InputStream imageStream = new FileInputStream((uploadFolder + "/" + waste.getImageName()));
            imageByteArray = imageStream.readAllBytes();
            imageStream.close();
        }

        return new GetWasteDetail(
                member.getNickname(),
                member.getAddress(),
                waste.getName(),
                waste.getPrice(),
                getTimeGap(waste),
                imageByteArray
        );
    }
}
