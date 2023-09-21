package com.authenticationservice.external.email.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class EmailConfirmDto {
    @Email
    private String email;
    private String code;
}
