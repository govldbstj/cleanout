package com.backend.waste.controller;

import com.backend.member.domain.MemberSession;
import com.backend.util.annotation.Login;
import com.backend.waste.service.WasteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class WasteController {

    private final WasteService wasteService;

    @PostMapping("/waste-management/image")
    public ResponseEntity<Void> getWasteImage(@RequestParam("image") MultipartFile file,
                                              @Login MemberSession memberSession) throws IOException {
        wasteService.postWasteImage(memberSession.getId(), file);
        return ResponseEntity.ok().build();
    }
}
