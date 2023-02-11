package com.backend.kakao.controller;

import com.backend.kakao.service.KakaoService;
import com.backend.member.domain.Member;
import com.backend.member.domain.MemberSession;
import com.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
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
        kakaoService.checkNeedToSignup(accessToken, userInfo);
        Member member = kakaoService.updateAccessToken(accessToken, userInfo);
        MemberSession memberSession = MemberSession.getFromMember(member);
        memberService.makeSessionForMemberSession(memberSession, httpServletRequest);
    }
}
