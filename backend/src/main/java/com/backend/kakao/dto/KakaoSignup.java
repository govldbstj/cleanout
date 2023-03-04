package com.backend.kakao.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class KakaoSignup {

    private String nickname;
    private String email;
    private String accessToken;


    @Builder
    public KakaoSignup(String nickname, String email, String accessToken) {
        this.nickname = nickname;
        this.email = email;
        this.accessToken = accessToken;
    }

    public static KakaoSignup getFromUserInfo(HashMap<String, Object> userInfo, String accessToken) {
        return KakaoSignup.builder()
                .nickname((String) userInfo.get("nickname"))
                .email((String) userInfo.get("email"))
                .accessToken(accessToken)
                .build();
    }
}
