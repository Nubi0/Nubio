package com.safeservice.api.chat.controller;

import com.safeservice.api.ApiResponseEntity;
import com.safeservice.api.chat.dto.ChatClientDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ChatRegion API", description = "채팅 위치 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/safe")
public class ChatCleintController {

//    public ApiResponseEntity<ChatClientDto>
}
