package com.authenticationservice.external.email.service.impl;

import com.authenticationservice.api.auth.exception.InvalidEmailException;
import com.authenticationservice.api.member.service.MemberInfoService;
import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.type.Email;
import com.authenticationservice.external.email.dto.request.EmailConfirmDto;
import com.authenticationservice.external.email.dto.request.EmailReqDto;
import com.authenticationservice.external.email.exception.ValidEmailException;
import com.authenticationservice.external.email.service.EmailService;
import com.authenticationservice.global.config.EmailConfig;
import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service("EmailService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final MemberInfoService memberInfoService;
    private final RedisUtil redisUtil;
    private final EmailConfig emailConfig;
    private final JavaMailSender javaMailSender;

    @Override
    @Transactional
    public String certifyEmail(EmailReqDto emailDto) {
        Optional<Member> member = memberInfoService.findByEmail(Email.from(emailDto.getEmail()));

        String code = makeCode();

        if (member.isPresent()) {
            if (member.get().getActive().getValue()) throw new ValidEmailException(ErrorCode.EMAIL_IS_EXISTS);
        }
        else{
            redisUtil.setEmail(emailDto.getEmail(), code, System.currentTimeMillis() + Long.parseLong(emailConfig.getEmailExpirationTime()));
        }
        sendCodeToEmail(code, emailDto.getEmail());

        return code;
    }

    @Override
    public void verifyEmail(EmailConfirmDto emailConfirmDto) {
        if (isVerify(emailConfirmDto)) {
            throw new InvalidEmailException(ErrorCode.EMAIL_CONFIRM_FAILED);
        }
        redisUtil.deleteEmail(emailConfirmDto.getEmail());
    }

    private boolean isVerify(EmailConfirmDto emailConfirmDto) {
        return !(redisUtil.hasKeyEmail(emailConfirmDto.getEmail()) &&
                redisUtil.getEmail(emailConfirmDto.getEmail())
                        .equals(emailConfirmDto.getCode()));
    }

    private String makeCode() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 4; i++) {
            code += random.nextInt(10);
        }
        return code;
    }

    private void sendCodeToEmail(String code, String to) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            message.setFrom(new InternetAddress(emailConfig.getAddress()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("NUBIO 인증코드입니다.");
            message.setText("인증코드: " + code);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
