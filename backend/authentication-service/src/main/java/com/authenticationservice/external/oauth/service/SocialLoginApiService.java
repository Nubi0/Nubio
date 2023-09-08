package com.authenticationservice.external.oauth.service;

import com.authenticationservice.external.oauth.model.OAuthAttributes;

public interface SocialLoginApiService {

    OAuthAttributes getUserInfo(String accessToken);
}
