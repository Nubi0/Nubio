package com.authenticationservice.external.email.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailReqDto {
    @Email
    private String email;
}
