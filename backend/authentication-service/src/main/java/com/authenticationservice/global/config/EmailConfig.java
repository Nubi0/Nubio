package com.authenticationservice.global.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Getter
@Configuration
public class EmailConfig {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.email-expiration-time}")
    private String emailExpirationTime;

    @Value("${mail.address}")
    private String address;

    @Value("${mail.password}")
    private String password;


    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host); // SMTP 서버 호스트
        mailSender.setPort(port); // SMTP 서버 포트
        mailSender.setUsername(address); // 이메일 계정
        mailSender.setPassword(password); // 이메일 비밀번호

        return mailSender;
    }
}





