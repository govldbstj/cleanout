package com.backend.waste.controller;

import com.backend.member.domain.MemberSession;
import com.backend.util.annotation.Login;
import com.backend.waste.dto.request.PatchWaste;
import com.backend.waste.dto.response.GetWasteBrief;
import com.backend.waste.dto.response.GetWasteDetail;
import com.backend.waste.service.WasteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/waste-management")
public class WasteController {

    private final WasteService wasteService;

    /**
     * 폐기물 등록 API
     * [POST] /waste-management/image
     */
    @PostMapping("/image")
    public ResponseEntity<Void> createWaste(@RequestParam(value = "image") MultipartFile image,
                                            @Login MemberSession memberSession) throws IOException {
        wasteService.createWaste(memberSession.getId(), image);

        return ResponseEntity.ok().build();
    }

    /**
     * 등록한 폐기물들 조회 API
     * [GET] /waste-management/waste
     */
    @GetMapping("/waste")
    public ResponseEntity<List<GetWasteBrief>> getWasteList(@Login MemberSession memberSession) {
        List<GetWasteBrief> getWasteBriefs = wasteService.getWasteList(memberSession.getId());
        return ResponseEntity.ok(getWasteBriefs);
    }

    /**
     * 폐기물 상세조회 API
     * [GET] /waste-management/waste/{wasteIdx}
     */
    @GetMapping("/waste/{wasteIdx}")
    public ResponseEntity<GetWasteDetail> getWaste(@Login MemberSession memberSession, @PathVariable("wasteIdx") Long wasteIdx) throws IOException {
        GetWasteDetail getWasteDetail = wasteService.getWaste(memberSession.getId(), wasteIdx);
        return ResponseEntity.ok(getWasteDetail);
    }

    /** [POST] /waste-management/ML
     *  -> 가상의 ML 서버에 해당하며, ML서버가 연결되지 않았을 때 기능 구현을 위해 추가한 것이다.
     *
     *  항상 wasteName값으로 냉장고-300L 이상, price 값으로 4000원을 응답한다.
     */
    @PostMapping("ML")
    public PatchWaste test() {
        PatchWaste patchWaste = new PatchWaste("냉장고-300L 이상",4000);
        return patchWaste;
    }
}
