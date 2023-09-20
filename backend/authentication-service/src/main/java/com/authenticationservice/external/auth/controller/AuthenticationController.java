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
@RequestMapping("/jwt")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtManager jwtManager;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public RedirectView handleAllRequests(@RequestBody(required = false) Map<String, Object> requestBody,
                                          @RequestHeader(value = "Authorization", required = false) String authHeader,
                                          @RequestHeader("x-forwarded-path") String originalRequestUrl) {

        HttpHeaders headers = new HttpHeaders();

        int index = originalRequestUrl.indexOf("/v1");
        log.info("index : {}", index);
        originalRequestUrl = originalRequestUrl.substring(index);

        headers.add("Location", originalRequestUrl);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String accessToken = authHeader.split(" ")[1];

            Claims claims = jwtManager.getTokenClaims(accessToken);

            String identification = claims.get("identification", String.class);
            String role = claims.get("role", String.class);

            // 리다이렉션 URL을 먼저 초기화
            String redirectToUrl = UriComponentsBuilder.fromUriString(originalRequestUrl)
                    .build()
                    .toUriString();

            // 속성 추가
            RedirectView redirectView = new RedirectView(redirectToUrl, true);
            redirectView.addStaticAttribute("X-Identification", identification);
            redirectView.addStaticAttribute("X-Role", role);

            log.info("headers: {}", headers.entrySet().stream().toList());
            log.info("request: {}", requestBody);

            return redirectView;
        }

        log.info("headers: {}", headers.entrySet().stream().toList());
        log.info("request: {}", requestBody);

        // 리다이렉션 URL을 먼저 초기화
        String redirectToUrl = UriComponentsBuilder.fromUriString(originalRequestUrl)
                .build()
                .toUriString();

        return new RedirectView(redirectToUrl, true);
    }
}
