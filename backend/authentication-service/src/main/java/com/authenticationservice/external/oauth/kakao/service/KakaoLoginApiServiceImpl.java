package com.authenticationservice.external.oauth.kakao.service;

import com.authenticationservice.domain.member.entity.constant.OAuthType;
import com.authenticationservice.external.oauth.kakao.client.KakaoUserInfoClient;
import com.authenticationservice.external.oauth.kakao.dto.KakaoUserInfoResDto;
import com.authenticationservice.external.oauth.model.OAuthAttributes;
import com.authenticationservice.external.oauth.service.SocialLoginApiService;
import com.authenticationservice.global.jwt.constant.GrantType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class KakaoLoginApiServiceImpl implements SocialLoginApiService {

    private final KakaoUserInfoClient kakaoUserInfoClient;
    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf8";

    @Override
    public OAuthAttributes getUserInfo(String accessToken) { // kakao에서 받은 accessToken
        // kakao로 kakao-accessToken 보내서 유저 정보를 받음
        log.info("Kakao 유저 정보 서버로 Kakao access-token을 보내 유저 정보 요청 시작");
        KakaoUserInfoResDto kakaoUserInfoResponseDto = kakaoUserInfoClient.getKakaoUserInfo(CONTENT_TYPE,
                GrantType.BEARER.getType() + " " + accessToken, true);
        log.info("Kakao 유저 정보 서버에서 유저 정보 응답");
        // 필요한 유저 정보 뽑아냄
        KakaoUserInfoResDto.KakaoAccount kakaoAccount = kakaoUserInfoResponseDto.getKakaoAccount();
        String email = kakaoAccount.getEmail();

        return OAuthAttributes.builder()
                .email(!StringUtils.hasText(email) ? kakaoUserInfoResponseDto.getId() : email)
                .nickname(kakaoAccount.getProfile().getNickname())
                .profileUrl(kakaoAccount.getProfile().getThumbnailImageUrl())
                .oauthType(OAuthType.KAKAO)
                .build();
    }
}
