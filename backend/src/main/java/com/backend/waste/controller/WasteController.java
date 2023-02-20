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

    @PostMapping("ML")
    public PatchWaste test() {
        PatchWaste patchWaste = new PatchWaste("냉장고-300L 이상",4000);
        return patchWaste;
    }

    @PostMapping("/image")
    public ResponseEntity<Void> createWaste(@RequestParam(value = "image") MultipartFile image,
                                               @Login MemberSession memberSession) throws IOException {
        wasteService.createWaste(memberSession.getId(),image);

        return ResponseEntity.ok().build();
    }

//    @PatchMapping("/waste")
//    public ResponseEntity<Void> updateWaste(@RequestBody PatchWaste patchWaste) {
//        wasteService.updateWaste(patchWaste);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/waste")
    public ResponseEntity<List<GetWasteBrief>> getWasteList(@Login MemberSession memberSession) {
        List<GetWasteBrief> getWasteBriefs = wasteService.getWasteList(memberSession.getId());
        return ResponseEntity.ok(getWasteBriefs);
    }

    @GetMapping("/waste/{wasteIdx}")
    public ResponseEntity<GetWasteDetail> getWaste(@Login MemberSession memberSession, @PathVariable("wasteIdx") Long wasteIdx) throws IOException {
        GetWasteDetail getWasteDetail = wasteService.getWaste(memberSession.getId(), wasteIdx);
        return ResponseEntity.ok(getWasteDetail);
    }
}
