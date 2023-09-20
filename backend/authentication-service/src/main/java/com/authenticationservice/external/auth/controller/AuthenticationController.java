package com.authenticationservice.external.auth.controller;

import com.authenticationservice.global.jwt.service.JwtManager;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtManager jwtManager;

    @RequestMapping("/jwt")
    public ResponseEntity<?> handleAllRequests(@RequestBody(required = false) Map<String, Object> requestBody,
                                               @RequestHeader(value = "Authorization", required = false) String authHeader,
                                                HttpServletRequest request){
        HttpHeaders headers = new HttpHeaders();
        String fullURL = request.getRequestURL().toString();
        log.info("fulURL : {}", fullURL);

        int index = fullURL.indexOf("/v1");
        log.info("index : {}", index);
        String originalRequestUrl = fullURL.substring(index);

        headers.add("Location", originalRequestUrl);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String accessToken = authHeader.split(" ")[1];

            Claims claims = jwtManager.getTokenClaims(accessToken);

            String identification = claims.get("identification", String.class);
            String role = claims.get("role", String.class);

            headers.add("X-Identification", identification);
            headers.add("X-Role", role);
        }

        return new ResponseEntity<>(requestBody, headers, HttpStatus.FOUND);

    }
}