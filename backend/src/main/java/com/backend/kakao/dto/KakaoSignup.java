package com.backend.kakao.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class KakaoSignup {

    private String nickname;
    private String email;

    @Builder
    public KakaoSignup(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public static KakaoSignup getFromUserInfo(HashMap<String, Object> userInfo) {
        return KakaoSignup.builder()
                .nickname((String) userInfo.get("nickname"))
                .email((String) userInfo.get("email"))
                .build();
    }
}
