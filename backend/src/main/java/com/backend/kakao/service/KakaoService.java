package com.backend.kakao.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    @Value("${kakao.signupUrl}")
    private String kakaoSignupUrl;

    @Value("${kakao.loginUrl}")
    private String kakaoLoginUrl;

    @Value("${kakao.logoutUrl}")
    private String kakaoLogoutUrl;

    public String getAccessToken(String authorizeCode) {
        String accessToken = EMPTY;

        try {
            HttpURLConnection conn = getHttpURLConnection();
            BufferedWriter bw = getBufferedWriter(authorizeCode, conn);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = EMPTY;
            String result = EMPTY;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {});
            accessToken = jsonMap.get("access_token").toString();

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessToken;
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

    private HttpURLConnection getHttpURLConnection() throws IOException {
        URL url = new URL(requestUrlForToken);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        return conn;
    }

    public HashMap<String, Object> getUserInfo(String accessToken) {
        HashMap<String, Object> userInfo = new HashMap<String, Object>();

        try {
            BufferedReader br = getBufferedReader(accessToken);
            String line = EMPTY;
            String result = EMPTY;

            while ((line = br.readLine()) != null) {
                result += line;
            }

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
                });

                Map<String, Object> properties = (Map<String, Object>) jsonMap.get("properties");
                Map<String, Object> kakao_account = (Map<String, Object>) jsonMap.get("kakao_account");

                String nickname = properties.get("nickname").toString();
                String email = kakao_account.get("email").toString();
                userInfo.put("nickname", nickname);
                userInfo.put("email", email);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    private BufferedReader getBufferedReader(String access_Token) throws IOException {
        URL url = new URL(requestUrlForUserInfo);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        return br;
    }
}
