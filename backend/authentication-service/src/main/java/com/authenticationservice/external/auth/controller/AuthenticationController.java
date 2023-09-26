package com.authenticationservice.external.auth.controller;

import com.authenticationservice.global.config.WebClientConfig;
import com.authenticationservice.global.jwt.service.JwtManager;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/jwt")
@RequiredArgsConstructor
@Tag(name = "5. KONG API", description = "KONG jwt 인증 api")
public class AuthenticationController {

    private final JwtManager jwtManager;
    private final WebClientConfig webClientConfig;
    private static HttpHeaders headers;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
    public Mono<ResponseEntity<?>> handleAllRequests(@RequestBody(required = false) Map<String, Object> requestBody,
                                                     HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        String originalRequestUrl = request.getHeader("x-forwarded-path");
        String requestMethod = request.getMethod();

        headers = new HttpHeaders();

        setPreHeader(request);

        if (!originalRequestUrl.contains("/start"))  {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                setClaimHeader(authHeader);
            }
            log.info("headers: {}, \n request: {}", headers.entrySet().stream().toList(), requestBody);
        }

        originalRequestUrl = setPath(originalRequestUrl);

        return sendRealRequest(requestBody, originalRequestUrl, requestMethod);
    }

    @GetMapping("/claim")
    public Map<String, String> getClaim(@RequestParam("Authorization") String auth) {
        String accessToken = auth.split(" ")[1];
        Claims claims = jwtManager.getTokenClaims(accessToken);

        String identification = claims.get("identification", String.class);
        String role = claims.get("role", String.class);

        Map map = new HashMap<>();
        map.put("identification", identification);
        map.put("role", role);

        return map;
    }

    private void setPreHeader(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headers.add(headerName, headerValue);
        }
    }

    private void setClaimHeader(String authHeader) {
        String accessToken = authHeader.split(" ")[1];

        Claims claims = jwtManager.getTokenClaims(accessToken);

        String identification = claims.get("identification", String.class);
        String role = claims.get("role", String.class);

        headers.add("X-Identification", identification);
        headers.add("X-Role", role);
    }

    private String setPath(String originalRequestUrl) {
        int index = originalRequestUrl.indexOf("/v1");
        log.info("index : {}", index);
        originalRequestUrl = originalRequestUrl.substring(index);

        headers.add("Location", originalRequestUrl);
        return originalRequestUrl;
    }

    private Mono<ResponseEntity<?>> sendRealRequest(Map<String, Object> requestBody, String originalRequestUrl, String requestMethod) {
        return webClientConfig.authClientBuilder().build()
                .method(HttpMethod.valueOf(requestMethod))
                .uri(originalRequestUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(requestBody != null ? BodyInserters.fromValue(requestBody) : BodyInserters.empty())
                .retrieve()
                .toEntity(String.class)
                .map(responseEntity -> {
                    return ResponseEntity.status(responseEntity.getStatusCode())
                            .headers(responseEntity.getHeaders())
                            .body(responseEntity.getBody());
                });
    }

}
