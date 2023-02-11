package com.backend.kakao.service;

import com.backend.kakao.dto.KakaoSignup;
import com.backend.member.domain.Member;
import com.backend.util.ServiceTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class KakaoServiceTest extends ServiceTest {

    @Autowired
    private KakaoService kakaoService;

    @Value("${kakao.request.url.token}")
    private String requestUrlForToken;

    @Test
    @DisplayName("가져온 카카오 정보로 회원가입이 진행됩니다")
    void kakaoSignup() {
        // given
        KakaoSignup kakaoSignup = KakaoSignup.builder()
                .nickname("카카오 회원 닉네임")
                .email("xxx@gmail.com")
                .accessToken("accessToken")
                .build();

        // when
        kakaoService.kakaoSignup(kakaoSignup);

        // then
        assertThat(memberRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("카카오 회원의 accessToken을 업데이트합니다")
    void updateAccessToken() {
        // given
        Member member = saveMemberInRepository();
        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("nickname", "닉네임");
        userInfo.put("email", "xxx@gmail.com");

        // when
        kakaoService.updateAccessToken("updateAccessToken", userInfo);

        // then
        assertThat(member.getNickname()).isEqualTo("닉네임");
        assertThat(member.getEmail()).isEqualTo("xxx@gmail.com");
        assertThat(member.getAccessToken()).isEqualTo("updateAccessToken");
    }

    @Test
    @DisplayName("가져온 카카오 정보가 가입된 회원이 아니라면 회원가입을 진행합니다")
    void checkNeedToSignup() {
        // given
        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("nickname", "닉네임");
        userInfo.put("email", "xxx@gmail.com");
        String accessToken = "accessToken";

        // when
        kakaoService.checkNeedToSignup(accessToken, userInfo);

        // then
        assertThat(memberRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("인가 코드가 잘못되었다면 예외가 발생합니다")
    void getAccessToken400() {
        // given
        String authorizeCode = "test";

        // expected
        Assertions.assertThrows(IOException.class,
                () -> kakaoService.getAccessToken(authorizeCode));
    }

    @Test
    @DisplayName("Json 정보로부터 access_token을 가져옵니다")
    void getAccessTokenFromJson() throws JsonProcessingException {
        // given
        String json = "{\"access_token\":\"dsfsafdsflsdufdsa\"}";

        // when
        String accessToken = kakaoService.getAccessTokenFromJson(json);

        // then
        assertThat(accessToken).isEqualTo("dsfsafdsflsdufdsa");
    }

    @Test
    @DisplayName("Json 정보로부터 Json 객체를 만듭니다")
    void getStringObjectMap() throws JsonProcessingException {
        // given
        String json = "{\"access_token\":\"dsfsafdsflsdufdsa\"}";

        // when
        Map<String, Object> stringObjectMap = kakaoService.getStringObjectMap(json);

        // then
        assertThat(stringObjectMap.get("access_token")).isEqualTo("dsfsafdsflsdufdsa");
    }
}