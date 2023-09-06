package com.authenticationservice.domain.member.entity;

import com.authenticationservice.domain.common.BaseTimeEntity;
import com.authenticationservice.domain.member.type.Email;
import com.authenticationservice.domain.member.type.Identification;
import com.authenticationservice.domain.member.type.Nickname;
import com.authenticationservice.domain.member.type.Password;
import jakarta.persistence.*;

import java.util.UUID;

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
}
