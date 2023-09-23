package com.authenticationservice.api.auth.service.impl;

import com.authenticationservice.api.auth.dto.request.LoginReqDto;
import com.authenticationservice.api.auth.dto.request.SignupReqDto;
import com.authenticationservice.api.auth.dto.response.SignResDto;
import com.authenticationservice.api.auth.exception.InvalidEmailException;
import com.authenticationservice.api.auth.exception.InvalidPasswordException;
import com.authenticationservice.api.auth.service.AuthService;
import com.authenticationservice.api.member.service.MemberInfoService;
import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.constant.Gender;
import com.authenticationservice.domain.member.entity.constant.OAuthType;
import com.authenticationservice.domain.member.entity.constant.Role;
import com.authenticationservice.domain.member.entity.type.*;
import com.authenticationservice.domain.member.repository.MemberRepository;
import com.authenticationservice.domain.member.service.MemberService;
import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.BusinessException;
import com.authenticationservice.global.jwt.dto.JwtDto;
import com.authenticationservice.global.jwt.service.JwtManager;
import com.authenticationservice.global.resolver.memberInfo.MemberInfoDto;
import com.authenticationservice.global.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service("authService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberService memberService;
    private final MemberInfoService memberInfoService;
    private final MemberRepository memberRepository;
    private final JwtManager jwtManager;
    private final PasswordEncoder passwordEncoder;
    private final RedisUtil redisUtil;

    @Override
    @Transactional
    public SignResDto signup(SignupReqDto signupReqDto) {
        Optional<Member> existMember = memberRepository.findByEmail(Email.from(signupReqDto.getEmail()));
        if(existMember.isPresent()) {
            throw new InvalidEmailException(ErrorCode.EMAIL_IS_EXISTS);
        }

        if(!signupReqDto.getPassword().equals(signupReqDto.getPasswordCheck()))
            throw new InvalidPasswordException(ErrorCode.INVALID_PASSWORD_CHECK);

        Member member = Member.builder()
                .identification(Identification.createIdentification())
                .email(Email.from(signupReqDto.getEmail()))
                .nickname(Nickname.from(signupReqDto.getNickname()))
                .password(Password.of(signupReqDto.getPassword(), passwordEncoder))
                .oAuthType(OAuthType.NUBIO)
                .role(Role.ROLE_USER)
                .gender(Gender.from(signupReqDto.getGender()))
                .birth(Birth.from(signupReqDto.getBirth()))
                .build();

        Member savedMember = memberService.register(member);
        JwtDto jwtDto = jwtManager.createJwtDto(String.valueOf(savedMember.getIdentification().getValue()), savedMember.getRole());

        return new SignResDto().of(jwtDto, savedMember.getRole());
    }

    @Override
    public SignResDto login(LoginReqDto loginReqDto) {
        Optional<Member> findMember = memberInfoService.findByEmail(Email.from(loginReqDto.getEmail()));
        if(findMember.isEmpty()) throw new InvalidEmailException(ErrorCode.MEMBER_NOT_EXISTS);
        Member member = findMember.get();
        if(!passwordEncoder.matches(loginReqDto.getPassword(), member.getPassword().getValue()))
            throw new BusinessException(ErrorCode.INVALID_PASSWORD_CHECK);
        JwtDto jwtDto = jwtManager.createJwtDto(String.valueOf(member.getIdentification().getValue()), member.getRole());

        return new SignResDto().of(jwtDto, member.getRole());
    }

    @Override
    @Transactional
    public void logout(MemberInfoDto memberInfo, String authorizationHeader) {
        String accessToken = authorizationHeader.split(" ")[1];
        Identification identification = Identification.from(memberInfo.getIdentification());

        Member member = memberInfoService.findByIdentification(identification);

        member.setRefreshTokenExpirationTime(LocalDateTime.now());

        // redis에 black-list 등록
        Long tokenExpiration = jwtManager.getTokenExpiration(accessToken);
        redisUtil.setBlackList(accessToken, "access-token", tokenExpiration);
    }
}
