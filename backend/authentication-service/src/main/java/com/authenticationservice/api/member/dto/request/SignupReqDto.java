package com.authenticationservice.api.member.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupReqDto {

    private String email;
    private String password;
    private String passwordCheck;
    private String nickname;
    private String gender;
    private String birth;

}
