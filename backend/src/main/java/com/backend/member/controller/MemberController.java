package com.backend.member.controller;

import com.backend.member.dto.request.MemberSignup;
import com.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody MemberSignup memberSignup) {
        memberService.validateDuplication(memberSignup);
        memberService.signup(memberSignup);
        return ResponseEntity.ok().build();
    }
}
