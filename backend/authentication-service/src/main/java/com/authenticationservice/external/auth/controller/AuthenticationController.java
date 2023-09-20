package com.authenticationservice.external.auth.controller;

import com.authenticationservice.global.jwt.service.JwtManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtManager jwtManager;

    @GetMapping("/jwt")
    public ResponseEntity<?> handleRequest(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestHeader("X-Original-Request-URL") String originalRequestUrl
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", originalRequestUrl);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String accessToken = authHeader.split(" ")[1];

            Claims claims = jwtManager.getTokenClaims(accessToken);

            String identification = claims.get("identification", String.class);
            String role = claims.get("role", String.class);

            headers.add("X-Identification", identification);
            headers.add("X-Role", role);
        }

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}