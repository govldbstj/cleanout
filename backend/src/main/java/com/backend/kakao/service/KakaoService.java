package com.backend.kakao.service;

import com.backend.kakao.dto.KakaoLogin;
import com.backend.kakao.dto.KakaoSignup;
import com.backend.member.domain.Member;
import com.backend.member.repository.MemberRepository;
import com.backend.member.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.apache.logging.log4j.util.Strings.EMPTY;

@RequiredArgsConstructor
@Service
public class KakaoService {

    @Value("${kakao.request.url.token}")
    private String requestUrlForToken;

    @Value("${kakao.request.url.userInfo}")
    private String requestUrlForUserInfo;

    @Value("${kakao.clientId}")
    private String clientId;

    @Value("${kakao.redirectUri}")
    private String redirectUri;

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Transactional
    public void kakaoSignup(KakaoSignup kakaoSignup) {
        Member member = Member.getFromKakaoSignup(kakaoSignup);
        memberRepository.save(member);
    }

    @Transactional
    public Member updateAccessToken(String accessToken, HashMap<String, Object> userInfo) {
        KakaoLogin kakaoLogin = KakaoLogin.getFromUserInfo(userInfo);
        Member member = getByKakaoLogin(kakaoLogin);
        member.updateAccessToken(accessToken);
        return member;
    }

    public Member getByKakaoLogin(KakaoLogin kakaoLogin) {
        return memberRepository.getByNicknameAndEmail(kakaoLogin.getNickname(), kakaoLogin.getEmail());
    }

    public void checkNeedToSignup(String accessToken, HashMap<String, Object> userInfo) {
        if (memberService.needToSignup(userInfo) == true) {
            KakaoSignup kakaoSignup = KakaoSignup.getFromUserInfo(userInfo, accessToken);
            this.kakaoSignup(kakaoSignup);
        }
    }

    public String getAccessToken(String authorizeCode) throws IOException {
        HttpURLConnection conn = getHttpURLConnection();
        BufferedWriter bw = getBufferedWriter(authorizeCode, conn);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        String json = EMPTY;
        while ((line = br.readLine()) != null) {
            json += line;
        }
        String accessToken = getAccessTokenFromJson(json);
        br.close();
        bw.close();

        return accessToken;
    }

    private HttpURLConnection getHttpURLConnection() throws IOException {
        URL url = new URL(requestUrlForToken);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        return conn;
    }

    private BufferedWriter getBufferedWriter(String authorizeCode, HttpURLConnection conn) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=authorization_code");
        sb.append("&client_id=" + clientId);
        sb.append("&redirect_uri=" + redirectUri);
        sb.append("&code=" + authorizeCode);
        bw.write(sb.toString());
        bw.flush();
        return bw;
    }

    protected String getAccessTokenFromJson(String json) throws JsonProcessingException {
        Map<String, Object> jsonMap = getStringObjectMap(json);
        String accessToken = jsonMap.get("access_token").toString();
        return accessToken;
    }


    public HashMap<String, Object> getUserInfoFromAccessToken(String accessToken) throws IOException {
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        checkReadLine(accessToken, userInfo);
        return userInfo;
    }

    private void checkReadLine(String accessToken, HashMap<String, Object> userInfo) throws IOException {
        BufferedReader br = getBufferedReader(accessToken);
        String line;
        String result = EMPTY;
        while ((line = br.readLine()) != null) {
            result += line;
        }
        Map<String, Object> jsonMap = getStringObjectMap(result);
        putUserInfo(userInfo, jsonMap);
    }

    private BufferedReader getBufferedReader(String access_Token) throws IOException {
        URL url = new URL(requestUrlForUserInfo);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        return br;
    }

    protected Map<String, Object> getStringObjectMap(String result) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {});
        return jsonMap;
    }

    private void putUserInfo(HashMap<String, Object> userInfo, Map<String, Object> jsonMap) {
        Map<String, Object> properties = (Map<String, Object>) jsonMap.get("properties");
        Map<String, Object> kakao_account = (Map<String, Object>) jsonMap.get("kakao_account");

        String nickname = properties.get("nickname").toString();
        String email = kakao_account.get("email").toString();
        userInfo.put("nickname", nickname);
        userInfo.put("email", email);
    }
}
