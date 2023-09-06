package com.authenticationservice.domain.member.constant;

import com.authenticationservice.domain.member.exception.InvalidOAuthTypeException;
import com.authenticationservice.global.error.ErrorCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum OAuthType {

    NUBIO("누비오"),
    KAKAO("카카오")
    ;
    private String description;

    OAuthType(String description) {
        this.description = description;
    }

    public static OAuthType from(String oautType) {
        validate(oautType);
        return OAuthType.valueOf(oautType.toUpperCase());
    }

    public static boolean isOAuthType(String oautType) {
        List<OAuthType> states = Arrays.stream(OAuthType.values())
                .filter(memberState -> memberState.name().equals(oautType))
                .collect(Collectors.toList());

        return states.size() != 0;
    }

    private static void validate(String oautType) {
        if(!OAuthType.isOAuthType(oautType.toUpperCase())) {
            throw new InvalidOAuthTypeException(ErrorCode.INVALID_OAUTHTYPE);
        }
    }
}
