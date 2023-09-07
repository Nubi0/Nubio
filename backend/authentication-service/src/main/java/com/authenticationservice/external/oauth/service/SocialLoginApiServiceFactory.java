package com.authenticationservice.external.oauth.service;

import com.authenticationservice.domain.member.entity.constant.OAuthType;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SocialLoginApiServiceFactory {

    private static Map<String, SocialLoginApiService> socialLoginApiServices;

    public SocialLoginApiServiceFactory(Map<String, SocialLoginApiService> socialLoginApiServices) {
        this.socialLoginApiServices = socialLoginApiServices;
    }

    public static SocialLoginApiService getSocialLoginApiService(OAuthType oauthType) {
        String socialLoginApiServiceBeanName = "";

        // 아래에 계속 추가해나가면 됨
        if(OAuthType.KAKAO.equals(oauthType)) {
            socialLoginApiServiceBeanName = "kakaoLoginApiServiceImpl";
        }

        return socialLoginApiServices.get(socialLoginApiServiceBeanName);
    }

}
