package com.authenticationservice.global.interceptor;

import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.AuthenticationException;
import com.authenticationservice.global.jwt.constant.TokenType;
import com.authenticationservice.global.jwt.service.JwtManager;
import com.authenticationservice.global.util.AuthorizationHeaderUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final JwtManager jwtManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. Authorization Header 검증
        String authorizationHeader = request.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);

        // 2. 토큰 검증
        String token = authorizationHeader.split(" ")[1];
        jwtManager.validateToken(token);

        // 3. 토큰 타입 (AccessToken 일 때만 통과)
        Claims tokenClaims = jwtManager.getTokenClaims(token);
        String tokenType = tokenClaims.getSubject();
        if(!TokenType.isAccessToken(tokenType)) {
            throw new AuthenticationException(ErrorCode.NOT_ACCESS_TOKEN_TYPE);
        }

        return true;
    }
}
