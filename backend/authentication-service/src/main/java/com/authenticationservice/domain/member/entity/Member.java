package com.authenticationservice.domain.member.entity;

import com.authenticationservice.domain.common.BaseTimeEntity;
import com.authenticationservice.domain.profile.entity.Profile;
import com.authenticationservice.domain.member.entity.constant.Gender;
import com.authenticationservice.domain.member.entity.constant.OAuthType;
import com.authenticationservice.domain.member.entity.constant.Role;
import com.authenticationservice.domain.member.entity.type.*;
import com.authenticationservice.global.jwt.dto.JwtDto;
import com.authenticationservice.global.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
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

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    //@ColumnDefault("ROLE_USER")
    private Role role = Role.ROLE_USER;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Embedded
    private Birth birth;

    @Column(name = "refreshtoken", length = 500)
    private String refreshToken;

    @Column(name = "refreshtoken_expiration_time")
    private LocalDateTime refreshTokenExpirationTime;

    @Embedded
    private Active active;

    @Builder
    public Member(Long id, Identification identification, Email email, Nickname nickname,
                  Password password, OAuthType oAuthType, Profile profile, Role role,
                  Gender gender, Birth birth, String refreshToken, LocalDateTime refreshTokenExpirationTime) {
        this.id = id;
        this.identification = identification;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.oAuthType = oAuthType;
        this.profile = profile;
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

    public void updateImage(Profile profile) {
        this.profile = profile;
    }

    public void withdraw() {
        this.email.withdrawEmail();
        if (this.birth != null) this.birth.withdrawBirth();
        this.nickname.withdrawNickname();
        this.password.withdrawPassword();
        if (this.profile != null) this.profile.withdrawProfile();
        this.active.withdrawActive();
    }
}
