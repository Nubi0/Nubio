package com.authenticationservice.domain.member.entity;

import com.authenticationservice.domain.common.BaseTimeEntity;
import com.authenticationservice.domain.member.entity.constant.Gender;
import com.authenticationservice.domain.member.entity.constant.OAuthType;
import com.authenticationservice.domain.member.entity.constant.Role;
import com.authenticationservice.domain.member.entity.type.*;
import com.authenticationservice.global.jwt.dto.JwtDto;
import com.authenticationservice.global.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@SQLDelete(sql = "UPDATE member SET active = false WHERE identification = ?")
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
    //@ColumnDefault("ROLE_USER")
    private Role role = Role.ROLE_USER;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Embedded
    private Birth birth;

    @Column(name = "refreshtoken")
    private String refreshToken;

    @Column(name = "refreshtoken_expiration_time")
    private LocalDateTime refreshTokenExpirationTime;

    @Embedded
    private Active active;

    @Builder
    public Member(Long id, Identification identification, Email email, Nickname nickname,
                  Password password, OAuthType oAuthType, ProfileUrl profileUrl, Role role,
                  Gender gender, Birth birth, String refreshToken, LocalDateTime refreshTokenExpirationTime) {
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
        this.active = Active.from(true);
    }

    public void updateRefreshToken(JwtDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.refreshTokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }

    public void withdraw() {
        this.email.withdrawEmail();
        this.birth.withdrawBirth();
        this.nickname.withdrawNickname();
        this.password.withdrawPassword();
        if (this.profileUrl != null) this.profileUrl.withdrawProfileUrl();
        this.active.withdrawActive();
    }
}
