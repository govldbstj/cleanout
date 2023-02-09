package com.backend.kakao.controller;

import com.backend.kakao.dto.KakaoLogin;
import com.backend.kakao.dto.KakaoSignup;
import com.backend.kakao.service.KakaoService;
import com.backend.member.domain.Member;
import com.backend.member.domain.MemberSession;
import com.backend.member.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class KakaoController {

    private final KakaoService kakaoService;
    private final MemberService memberService;

    @GetMapping("/oauth")
    public void kakaoSignup(@RequestParam String code) throws JsonProcessingException {

        String access_Token = kakaoService.getAccessToken(code);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
        if (memberService.needToSignup(userInfo) == true) {
            kakaoService.requestSignup(userInfo);
        }
        kakaoService.requestLogin(userInfo);
    }

    @PostMapping("/kakao/signup")
    public ResponseEntity<Void> kakaoSignup(@RequestBody KakaoSignup kakaoSignup) {
        memberService.kakaoSignup(kakaoSignup);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/kakao/login")
    public ResponseEntity<Void> kakaoLogin(@RequestBody KakaoLogin kakaoLogin,
                           HttpServletRequest httpServletRequest) {
        Member member = memberService.getByKakaoLogin(kakaoLogin);
        MemberSession memberSession = MemberSession.getFromMember(member);
        memberService.makeSessionForMemberSession(memberSession, httpServletRequest);
        return ResponseEntity.ok().build();
    }
}
