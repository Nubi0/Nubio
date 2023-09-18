package com.authenticationservice.api.auth.dto.request;

import lombok.Builder;
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

    @Builder
    public SignupReqDto(String email, String password, String passwordCheck, String nickname, String gender, String birth) {
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.nickname = nickname;
        this.gender = gender;
        this.birth = birth;
    }
}
