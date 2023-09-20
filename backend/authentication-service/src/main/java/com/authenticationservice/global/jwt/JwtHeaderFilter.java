package com.authenticationservice.global.jwt;

import com.authenticationservice.global.jwt.service.JwtManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtHeaderFilter extends OncePerRequestFilter {

    private final JwtManager jwtManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getRequestURI().equals("/v1/member/me")) {
            //UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            String jwtToken = request.getHeader("authorization");
            String accessToken = jwtToken.split(" ")[1];

            Claims claims = jwtManager.getTokenClaims(accessToken);

            String identification = claims.get("identification", String.class);
            String role = claims.get("role", String.class);

            response.setHeader("X-Identification", identification);
            response.setHeader("X-Role", role);
            log.info("header : {}" , response.getHeader("X-Identification"));

            filterChain.doFilter(request, response);
        }
        filterChain.doFilter(request, response);
    }
}
