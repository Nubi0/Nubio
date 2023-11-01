package com.authenticationservice.global.interceptor;

import com.authenticationservice.domain.member.entity.constant.Role;
import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.AuthenticationException;
import com.authenticationservice.global.jwt.service.JwtManager;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AdminAuthorizationInterceptor implements HandlerInterceptor {

    private final JwtManager jwtManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorizationHeader = request.getHeader("Authorization");
        String accessToken = authorizationHeader.split(" ")[1];

        // 이전 인터셉터(AuthenticationInterceptor) 에서 토큰 검증은 했으므로 Role 이 ADMIN 인지만 확인

        Claims tokenClaims = jwtManager.getTokenClaims(accessToken);
        String role = (String)tokenClaims.get("role");
        if(!Role.ROLE_ADMIN.equals(Role.valueOf(role))) {
            throw new AuthenticationException(ErrorCode.FORBIDDEN_ADMIN);
        }

        return true;
    }

}
