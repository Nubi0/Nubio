package com.authenticationservice.global.config;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Slf4j
@Getter
@Configuration
public class EmailConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.email-expiration-time}")
    private String emailExpirationTime;

    @Value("${spring.mail.username}")
    private String address;

    @Value("${spring.mail.password}")
    private String password;


    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host); // SMTP 서버 호스트
        mailSender.setPort(587); // SMTP 서버 포트
        mailSender.setUsername(address); // 이메일 계정
        mailSender.setPassword(password); // 이메일 비밀번호

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        mailSender.setJavaMailProperties(props);

        return mailSender;
    }
}





