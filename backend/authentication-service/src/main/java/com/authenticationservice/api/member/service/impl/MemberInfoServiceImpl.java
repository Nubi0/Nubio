package com.authenticationservice.api.member.service.impl;

import com.authenticationservice.api.member.dto.request.SignupReqDto;
import com.authenticationservice.api.member.dto.response.SignupResDto;
import com.authenticationservice.api.member.exception.InvalidPasswordException;
import com.authenticationservice.api.member.service.MemberInfoService;
import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.constant.Gender;
import com.authenticationservice.domain.member.entity.constant.OAuthType;
import com.authenticationservice.domain.member.entity.constant.Role;
import com.authenticationservice.domain.member.entity.type.*;
import com.authenticationservice.domain.member.service.MemberService;
import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.jwt.dto.JwtDto;
import com.authenticationservice.global.jwt.service.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("memberInfoService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberInfoServiceImpl implements MemberInfoService {

    private final MemberService memberService;
    private final JwtManager jwtManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public SignupResDto signup(SignupReqDto signupReqDto) {

        if(!signupReqDto.getPassword().equals(signupReqDto.getPasswordCheck()))
            throw new InvalidPasswordException(ErrorCode.INVALID_PASSWORD_CHECK);

        Member member = Member.builder()
                .identification(new Identification())
                .email(Email.from(signupReqDto.getEmail()))
                .nickname(Nickname.from(signupReqDto.getNickname()))
                .password(Password.of(signupReqDto.getPassword(), passwordEncoder))
                .oAuthType(OAuthType.NUBIO)
                .role(Role.ROLE_USER)
                .gender(Gender.from(signupReqDto.getGender()))
                .birth(Birth.from(signupReqDto.getBirth()))
                .build();

        Member savedMember = memberService.register(member);
        JwtDto jwtDto = jwtManager.createJwtDto(String.valueOf(savedMember.getIdentification()), savedMember.getRole());

        return new SignupResDto().of(jwtDto, savedMember.getRole());
    }
}
