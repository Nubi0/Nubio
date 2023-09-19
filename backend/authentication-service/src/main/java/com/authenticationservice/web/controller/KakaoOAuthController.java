package com.authenticationservice.web.controller;

import com.authenticationservice.api.ApiResponse;
import com.authenticationservice.domain.member.entity.constant.OAuthType;
import com.authenticationservice.web.client.KakaoTokenClient;
import com.authenticationservice.web.dto.KakaoAuthReqDto;
import com.authenticationservice.web.dto.KakaoTokenDto;
import com.authenticationservice.web.dto.OauthLoginResDto;
import com.authenticationservice.web.service.OauthLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/oauth")
@RequiredArgsConstructor
@Tag(name = "1. OAUTH API", description = " 소셜 로그인 api")
public class KakaoOAuthController {

    private final KakaoTokenClient kakaoTokenClient;
    private final OauthLoginService oauthLoginService;

    @Value("${oauth2.client.kakao.client_id}")
    private String clientId;

    @Value("${oauth2.client.kakao.client_secret}")
    private String clientSecret;

    @Value("${oauth2.client.kakao.authorization_grant_type}")
    private String grantType;

    @Operation(summary = "카카오 회원가입 및 로그인", description = "start/v1/oauth/kakao/callback\n\n" )
    @PostMapping("/kakao/callback")
    public ApiResponse<OauthLoginResDto> loginCallback(@RequestBody KakaoAuthReqDto request) {
        String contentType = "application/x-www-form-urlencoded;charset=utf-8"; // 공식 문서
        KakaoTokenDto.Req kakaoTokenRequestDto = KakaoTokenDto.Req.builder()
                .client_id(clientId)
                .client_secret(clientSecret)
                .grant_type(grantType)
                .code(request.getCode())
//                .redirect_uri("http://localhost:8080/oauth/kakao/callback")
                .redirect_uri(request.getRedirectUrl())
                .build();

        log.info("kakao 인증 서버에서 token 요청 시작");
        KakaoTokenDto.Res kakaoToken = kakaoTokenClient.requestKakaoToken(contentType, kakaoTokenRequestDto);
        log.info("kakao 인증 서버에서 token 응답 완료");

        log.info("kakao 유저 정보 서버에 access-token 보내서 유저 정보 요청 시작");
        OauthLoginResDto jwt = oauthLoginService.oauthLogin(kakaoToken.getAccess_token(), OAuthType.KAKAO);
        return ApiResponse.ok(jwt);
    }
}
