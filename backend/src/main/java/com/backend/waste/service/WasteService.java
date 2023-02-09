package com.backend.waste.service;


import com.backend.member.domain.Member;
import com.backend.member.exception.MemberNotFoundException;
import com.backend.member.repository.MemberJpaRepository;
import com.backend.waste.domain.Waste;
import com.backend.waste.dto.request.PatchWaste;
import com.backend.waste.repository.WasteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
}
