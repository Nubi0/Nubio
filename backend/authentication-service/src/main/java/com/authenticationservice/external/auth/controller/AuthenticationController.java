package com.authenticationservice.external.auth.controller;

import com.authenticationservice.global.WebClientConfig;
import com.authenticationservice.global.jwt.service.JwtManager;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/jwt")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtManager jwtManager;
    private final WebClientConfig webClientConfig;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public Mono<ResponseEntity<?>> handleAllRequests(@RequestBody(required = false) Map<String, Object> requestBody,
                                                     @RequestHeader(value = "Authorization", required = false) String authHeader,
                                                     @RequestHeader("x-forwarded-path") String originalRequestUrl,
                                                     HttpServletRequest request) {

        log.info(request.getHeader("Authorization"));
        log.info(request.getHeader("x-forwarded-path"));
        String requestMethod = request.getMethod();
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

            headers.add("X-Identification", identification);
            headers.add("X-Role", role);
        }

        log.info("headers: {}", headers.entrySet().stream().toList());
        log.info("request: {}", requestBody);

        return webClientConfig.authClientBuilder().build()
                .method(HttpMethod.valueOf(requestMethod))
                .uri(originalRequestUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .toEntity(String.class)
                .map(responseEntity -> {
                    return ResponseEntity.status(responseEntity.getStatusCode())
                            .headers(responseEntity.getHeaders())
                            .body(responseEntity.getBody());
                });
    }
}
