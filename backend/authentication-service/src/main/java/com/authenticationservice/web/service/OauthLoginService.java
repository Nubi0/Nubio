package com.authenticationservice.web.service;

import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.constant.OAuthType;
import com.authenticationservice.domain.member.entity.constant.Role;
import com.authenticationservice.domain.member.entity.type.Email;
import com.authenticationservice.domain.member.service.MemberService;
import com.authenticationservice.external.oauth.model.OAuthAttributes;
import com.authenticationservice.external.oauth.service.SocialLoginApiService;
import com.authenticationservice.external.oauth.service.SocialLoginApiServiceFactory;
import com.authenticationservice.global.jwt.dto.JwtDto;
import com.authenticationservice.global.jwt.service.JwtManager;
import com.authenticationservice.web.dto.OauthLoginResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OauthLoginService {

    private final MemberService memberService;
    private final JwtManager jwtManager;

    @Transactional
    public OauthLoginResDto oauthLogin(String accessToken, OAuthType oauthType) {
        SocialLoginApiService socialLoginApiService = SocialLoginApiServiceFactory.getSocialLoginApiService(oauthType);
        OAuthAttributes userInfo = socialLoginApiService.getUserInfo(accessToken);
        log.info("userInfo : {}",  userInfo);

        JwtDto jwtTokenDto;
        Optional<Member> optionalMember = memberService.findByEmail(Email.from(userInfo.getEmail()));
        Member oauthMember;
        if(optionalMember.isEmpty()) { // 신규 회원 가입
            log.info("kakao 신규 유저 회원가입");
            oauthMember = userInfo.toMemberEntity(oauthType, Role.ROLE_USER);
            oauthMember = memberService.register(oauthMember);
        } else { // 기존 회원일 경우
            log.info("kakao 기존 유저 로그인");
            oauthMember = optionalMember.get();
        }
        // 토큰 생성
        jwtTokenDto = jwtManager.createJwtDto(String.valueOf(oauthMember.getIdentification().getValue()), oauthMember.getRole());
        oauthMember.updateRefreshToken(jwtTokenDto);

        return OauthLoginResDto.of(jwtTokenDto, oauthMember.getRole());
    }

}
