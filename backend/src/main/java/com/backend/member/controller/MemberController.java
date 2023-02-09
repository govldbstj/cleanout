package com.backend.member.controller;

import com.backend.member.domain.Member;
import com.backend.member.domain.MemberSession;
import com.backend.member.dto.request.MemberLogin;
import com.backend.member.dto.request.MemberSignup;
import com.backend.member.dto.request.MemberUpdate;
import com.backend.member.dto.response.MemberResponse;
import com.backend.member.service.MemberService;
import com.backend.util.annotation.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("/login")
    public ResponseEntity<MemberResponse> login(@RequestBody MemberLogin memberLogin,
                                                HttpServletRequest httpServletRequest) {
        MemberSession memberSession = memberService.getMemberSession(memberLogin);
        memberService.makeSessionForMemberSession(memberSession, httpServletRequest);
        MemberResponse memberResponse = MemberResponse.getFromMemberSession(memberSession);
        return ResponseEntity.ok(memberResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Login MemberSession memberSession,
                                       HttpServletRequest httpServletRequest) {
        memberService.logout(memberSession, httpServletRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/member")
    public ResponseEntity<MemberResponse> update(@Login MemberSession memberSession,
                                                 @RequestBody MemberUpdate memberUpdate) {
        Member member = memberService.update(memberSession, memberUpdate);
        MemberResponse memberResponse = MemberResponse.getFromMember(member);
        return ResponseEntity.ok(memberResponse);
    }
}
