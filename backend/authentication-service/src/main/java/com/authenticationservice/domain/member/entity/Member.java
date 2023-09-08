package com.authenticationservice.domain.member.entity;

import com.authenticationservice.domain.common.BaseTimeEntity;
import com.authenticationservice.domain.member.entity.constant.Gender;
import com.authenticationservice.domain.member.entity.constant.OAuthType;
import com.authenticationservice.domain.member.entity.constant.Role;
import com.authenticationservice.domain.member.entity.type.*;
import com.authenticationservice.global.jwt.dto.JwtDto;
import com.authenticationservice.global.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Identification identification;

    @Embedded
    private Email email;

    @Embedded
    private Nickname nickname;

    @Embedded
    private Password password;

    @Enumerated(EnumType.STRING)
    @Column(name = "oauth_type", nullable = false)
    private OAuthType oAuthType;

    @Embedded
    private ProfileUrl profileUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.ROLE_USER;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Embedded
    private Birth birth;

    @Column(name = "refreshtoken")
    private String refreshToken;

    @Column(name = "refreshtoken_expiration_time")
    private LocalDateTime refreshTokenExpirationTime;

    @Embedded
    private Active active = Active.from(true);

    @Builder
    public Member(Long id, Identification identification, Email email, Nickname nickname,
                  Password password, OAuthType oAuthType, ProfileUrl profileUrl, Role role,
                  Gender gender, Birth birth, String refreshToken, LocalDateTime refreshTokenExpirationTime,
                  Active active) {
        this.id = id;
        this.identification = identification;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.oAuthType = oAuthType;
        this.profileUrl = profileUrl;
        this.role = role;
        this.gender = gender;
        this.birth = birth;
        this.refreshToken = refreshToken;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
        this.active = active;
    }

    public void updateRefreshToken(JwtDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.refreshTokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }
}
