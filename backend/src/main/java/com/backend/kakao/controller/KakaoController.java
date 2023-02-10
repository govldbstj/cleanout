package com.backend.kakao.controller;

import com.backend.kakao.dto.KakaoLogin;
import com.backend.kakao.dto.KakaoSignup;
import com.backend.kakao.service.KakaoService;
import com.backend.member.domain.Member;
import com.backend.member.domain.MemberSession;
import com.backend.member.dto.request.MemberUpdate;
import com.backend.member.repository.MemberRepository;
import com.backend.member.service.MemberService;
import com.backend.util.annotation.Login;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class KakaoController {

    private final KakaoService kakaoService;
    private final MemberService memberService;

    @GetMapping("/oauth")
    public void kakaoOauth(@RequestParam String code,
                           HttpServletRequest httpServletRequest) {

        String accessToken = kakaoService.getAccessToken(code);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(accessToken);
        if (memberService.needToSignup(userInfo) == true) {
            KakaoSignup kakaoSignup = KakaoSignup.getFromUserInfo(userInfo, accessToken);
            memberService.kakaoSignup(kakaoSignup);
        }
        KakaoLogin kakaoLogin = KakaoLogin.getFromUserInfo(userInfo);
        Member member = memberService.getByKakaoLogin(kakaoLogin);
        MemberSession memberSession = MemberSession.getFromMember(member);
        memberService.makeSessionForMemberSession(memberSession, httpServletRequest);
    }
}
