package com.authenticationservice.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final WebConfig webConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors
                        .configurationSource(webConfig.corsConfigurationSource()))
                .csrf(csrf -> csrf
                                .disable()
                )
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/v1/users/login", "/v1/users/signup", "/v1/oauth/**",  "/mysql-console/**", "/static/**", "/swagger-ui/**", "/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .disable())
                .logout(logout -> logout
                        .logoutSuccessUrl("/v1/users/login")
                        .permitAll()
                );
        return http.build();
    }

    // password encoder로 사용할 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder defaultEncoder = new BCryptPasswordEncoder();
        String idForEncode = "bcrypt";

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(idForEncode, defaultEncoder);

        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }


}
