package com.authenticationservice.global.util;


import com.authenticationservice.domain.member.exception.MemberNotFoundException;
import com.authenticationservice.global.error.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static boolean getAuthentication() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.isAuthenticated();
    }

    public static String getAuthorizedMember() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new MemberNotFoundException(ErrorCode.MEMBER_NOT_EXISTS);
        }
        return authentication.getName();
    }
}
