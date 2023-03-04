package com.backend.kakao.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class KakaoLogin {

    private String nickname;
    private String email;

    @Builder
    public KakaoLogin(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public static KakaoLogin getFromUserInfo(HashMap<String, Object> userInfo) {
        return KakaoLogin.builder()
                .nickname((String) userInfo.get("nickname"))
                .email((String) userInfo.get("email"))
                .build();
    }
}
