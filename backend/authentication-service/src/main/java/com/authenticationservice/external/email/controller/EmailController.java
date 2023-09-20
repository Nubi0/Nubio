package com.authenticationservice.external.email.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/email")
@RequiredArgsConstructor
@Tag(name = "2. Email API", description = "Email 인증 api")
public class EmailController {

}
