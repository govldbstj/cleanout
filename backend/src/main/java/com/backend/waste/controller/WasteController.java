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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/waste-management")
public class WasteController {

    private final WasteService wasteService;

//    @PostMapping("/image")
//    public ResponseEntity<Void> postWasteImage(@RequestParam("image") MultipartFile file,
//                                               @Login MemberSession memberSession) throws IOException {
//        wasteService.postWasteImage(memberSession.getId(), file);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/image")
    public ResponseEntity<Void> postWasteimage(@RequestParam(value = "image") List<MultipartFile> images,
                                               @Login MemberSession memberSession,
                                               @RequestParam String unique) throws IOException {
        wasteService.createWaste(memberSession.getId(),images,unique);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/waste")
    public ResponseEntity<Void> updateWaste(@RequestBody PatchWaste patchWaste) {
        wasteService.updateWaste(patchWaste);
        return ResponseEntity.ok().build();
    }

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
