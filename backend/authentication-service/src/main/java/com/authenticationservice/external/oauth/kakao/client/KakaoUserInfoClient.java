package com.authenticationservice.external.oauth.kakao.client;

import com.authenticationservice.external.oauth.kakao.dto.KakaoUserInfoResDto;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://kapi.kakao.com", name = "kakaoUserInfoClient")
public interface KakaoUserInfoClient {

    @GetMapping(value = "/v2/user/me", consumes = "application/json")
    KakaoUserInfoResDto getKakaoUserInfo(@RequestHeader("Content-type") String contentType,
                                         @RequestHeader("Authorization") String accessToken,
                                         @Param("secure_resource") Boolean secureResourceFlag);

}
