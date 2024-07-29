package org.backend.auth.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.backend.auth.dto.response.KakaoMemberResponse;
import org.backend.auth.dto.response.OAuthAccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoOauth2Client {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String KAKAO_CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URI;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String KAKAO_TOKEN_URI;

    @Value(("${spring.security.oauth2.client.provider.kakao.user-info-uri}"))
    private String KAKAO_USER_INFO_URI;

    private final RestTemplate restTemplate;

    public KakaoOauth2Client(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAccessToken(final String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("grant_type", "authorization_code");
        params.add("client_id", KAKAO_CLIENT_ID);
        params.add("redirect_uri", KAKAO_REDIRECT_URI);
        params.add("code", code);
        params.add("client_secret", KAKAO_CLIENT_SECRET);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
        ResponseEntity<String> accessTokenResponse = restTemplate.exchange(
                KAKAO_TOKEN_URI,
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        if (accessTokenResponse.getStatusCode() == HttpStatus.OK) {
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                OAuthAccessTokenResponse response = objectMapper.readValue(accessTokenResponse.getBody(), OAuthAccessTokenResponse.class);
                return response.accessToken();
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException("카카오 액세스 토큰을 가져오는데 실패했습니다.", e);
            }
        }
        return null;
    }

    public KakaoMemberResponse getMemberInfo(final String accessToken) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        final HttpEntity<Void> request = new HttpEntity<>(headers);

        final KakaoMemberResponse response = restTemplate.exchange(
                KAKAO_USER_INFO_URI,
                HttpMethod.GET,
                request,
                KakaoMemberResponse.class
        ).getBody();

        return response;
    }
}
