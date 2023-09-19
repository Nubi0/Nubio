package com.authenticationservice.global.config;

import com.authenticationservice.global.jwt.service.JwtManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${token.access-token-expiration-time}")
    private String accessTokenExpirationTime;

    @Value("${token.refresh-token-expiration-time}")
    private String refreshTokenExpirationTime;

    @Value("${token.secret}")
    private String tokenSecret;

    @Value("${token.issuer}")
    private String tokenIssuer;

    @Bean
    public JwtManager tokenManager() {
        return new JwtManager(accessTokenExpirationTime, refreshTokenExpirationTime, tokenSecret, tokenIssuer);
    }
}
