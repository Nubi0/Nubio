package com.authenticationservice.web.controller;

import com.authenticationservice.api.ApiResponse;
import com.authenticationservice.domain.member.entity.constant.OAuthType;
import com.authenticationservice.web.client.KakaoTokenClient;
import com.authenticationservice.web.dto.KakaoAuthReqDto;
import com.authenticationservice.web.dto.KakaoTokenDto;
import com.authenticationservice.web.dto.OauthLoginDto;
import com.authenticationservice.web.service.OauthLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/oauth")
@RequiredArgsConstructor
public class KakaoOAuthController {

    private final KakaoTokenClient kakaoTokenClient;
    private final OauthLoginService oauthLoginService;

    @Value("${oauth2.client.kakao.client_id}")
    private String clientId;

    @Value("${oauth2.client.kakao.client_secret}")
    private String clientSecret;

    @Value("${oauth2.client.kakao.authorization_grant_type}")
    private String grantType;

    @PostMapping("/kakao/callback")
    public ApiResponse<OauthLoginDto.Res> loginCallback(@RequestBody KakaoAuthReqDto request) {
        String contentType = "application/x-www-form-urlencoded;charset=utf-8"; // 공식 문서
        KakaoTokenDto.Req kakaoTokenRequestDto = KakaoTokenDto.Req.builder()
                .client_id(clientId)
                .client_secret(clientSecret)
                .grant_type(grantType)
                .code(request.getCode())
//                .redirect_uri("http://localhost:8080/oauth/kakao/callback")
                .redirect_uri(request.getRedirectUrl())
                .build();

        KakaoTokenDto.Res kakaoToken = kakaoTokenClient.requestKakaoToken(contentType, kakaoTokenRequestDto);

        OauthLoginDto.Res jwt = oauthLoginService.oauthLogin(kakaoToken.getAccess_token(), OAuthType.KAKAO);
        return ApiResponse.ok(jwt);
    }
}
