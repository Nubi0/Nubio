package com.authenticationservice.global;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${nubio.url}")
    private String baseUrl;

    @Value("${nubio.auth}")
    private String authUrl;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder().baseUrl(baseUrl);
    }


    @Bean
    public WebClient.Builder authClientBuilder() {
        return WebClient.builder().baseUrl(authUrl);
    }
}

