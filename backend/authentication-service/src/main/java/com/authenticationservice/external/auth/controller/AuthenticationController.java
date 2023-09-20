package com.authenticationservice.external.auth.controller;

import com.authenticationservice.global.jwt.service.JwtManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtManager jwtManager;

    @PostMapping("/jwt")
    public RedirectView handleAllRequests(@RequestBody(required = false) Map<String, Object> requestBody,
                                          @RequestHeader(value = "Authorization", required = false) String authHeader,
                                          @RequestHeader("x-forwarded-path") String originalRequestUrl){

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

        log.info("headers: {}", headers.entrySet().stream().toList());
        log.info("request: {}", requestBody);

        String redirectToUrl = UriComponentsBuilder.fromUriString(originalRequestUrl)
                .build()
                .toUriString();

        return new RedirectView(redirectToUrl, true);
    }
}
