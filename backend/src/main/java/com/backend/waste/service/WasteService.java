package com.backend.waste.service;


import com.backend.member.domain.Member;
import com.backend.member.exception.MemberNotFoundException;
import com.backend.member.repository.MemberJpaRepository;
import com.backend.waste.domain.Waste;
import com.backend.waste.dto.request.PatchWaste;
import com.backend.waste.dto.response.GetWasteBrief;
import com.backend.waste.dto.response.GetWasteDetail;
import com.backend.waste.exception.WasteNotFoundException;
import com.backend.waste.repository.WasteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class WasteService {

    private final WasteJpaRepository wasteJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

//    @Value("${spring.file.path}")
//    private String uploadFolder;

    @Transactional
    public void postWasteImage(Long memberIdx, MultipartFile file) throws IOException {
        Member member = memberJpaRepository.findById(memberIdx).orElseThrow(MemberNotFoundException::new);
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + file.getOriginalFilename();
        // 이미지를 서버 파일에 저장하는 과정
//        Path imageFilePath = Paths.get(uploadFolder + "/" + imageFileName);
//        Files.write(imageFilePath, file.getBytes());
        Waste waste = Waste.getWasteFromPostImage(member, file.getOriginalFilename());
        wasteJpaRepository.save(waste);
    }

    @Transactional
    public void updateWaste(PatchWaste patchWaste) {
        Waste waste = wasteJpaRepository.findByImageName(patchWaste.getImageName());
        waste.update(patchWaste.getWasteName(), patchWaste.getPrice());
    }

    @Transactional
    public List<GetWasteBrief> getWasteList(Long id) {
        Member member = memberJpaRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        List<Waste> wastes = wasteJpaRepository.findAllByMemberOrderByEnrolledDateDesc(member);

        List<GetWasteBrief> result = new ArrayList<GetWasteBrief>();
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
            if (ChronoUnit.DAYS.between(now, time) != 0){
                long dayGap = ChronoUnit.DAYS.between(now, time);
                sb.append(dayGap).append("일 ");
                time = time.minusDays(dayGap);
            }
            if (ChronoUnit.HOURS.between(now, time) != 0){
                long hourGap = ChronoUnit.HOURS.between(now, time);
                sb.append(hourGap).append("시간 ");
                time = time.minusHours(hourGap);
            }
            sb.append(ChronoUnit.MINUTES.between(now, time)).append("분 후 수거");
        }

        return sb.toString();
    }

    public GetWasteDetail getWaste(Long userIdx, Long wasteIdx) {
        Member member = memberJpaRepository.findById(userIdx).orElseThrow(MemberNotFoundException::new);
        Waste waste = wasteJpaRepository.findById(wasteIdx).orElseThrow(WasteNotFoundException::new);

        return new GetWasteDetail(
                member.getNickname(),
                member.getAddress(),
                waste.getName(),
                waste.getPrice(),
                getTimeGap(waste)
        );
    }
}
