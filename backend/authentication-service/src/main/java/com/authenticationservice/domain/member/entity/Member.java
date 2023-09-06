package com.authenticationservice.domain.member.entity;

import com.authenticationservice.domain.common.BaseTimeEntity;
import com.authenticationservice.domain.member.type.Email;
import com.authenticationservice.domain.member.type.Identification;
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


}
