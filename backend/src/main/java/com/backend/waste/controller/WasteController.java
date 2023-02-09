package com.backend.waste.controller;

import com.backend.member.domain.MemberSession;
import com.backend.util.annotation.Login;
import com.backend.waste.dto.request.PatchWaste;
import com.backend.waste.service.WasteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/waste-management")
public class WasteController {

    private final WasteService wasteService;

    @PostMapping("/image")
    public ResponseEntity<Void> postWasteImage(@RequestParam("image") MultipartFile file,
                                              @Login MemberSession memberSession) throws IOException {
        wasteService.postWasteImage(memberSession.getId(), file);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("waste")
    public ResponseEntity<Void> insertModelWaste(@RequestBody PatchWaste patchWaste) {
        wasteService.updateWaste(patchWaste);
        return ResponseEntity.ok().build();
    }
}
