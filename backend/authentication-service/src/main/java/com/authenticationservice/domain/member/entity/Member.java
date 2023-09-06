package com.authenticationservice.domain.member.entity;

import com.authenticationservice.domain.common.BaseTimeEntity;
import com.authenticationservice.domain.member.constant.Gender;
import com.authenticationservice.domain.member.constant.OAuthType;
import com.authenticationservice.domain.member.constant.Role;
import com.authenticationservice.domain.member.type.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
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
    private Role role;

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
    private Active active;
}
