package com.safeservice.api.chat.controller;

import com.safeservice.api.ApiResponseEntity;
import com.safeservice.api.chat.dto.ChatClientDto;
import com.safeservice.api.chat.dto.ChatRequestDto;
import com.safeservice.api.chat.service.ChatClientService;
import com.safeservice.api.emergencymessage.dto.EMReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ChatRegion API", description = "채팅 위치 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/safe")
public class ChatClientController {

    private final ChatClientService chatClientService;

    @Operation(summary = "지역 톡방 조회", description = "safe/v1/safe/chat\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/chat")
    public ApiResponseEntity<ChatClientDto> chatClient(@RequestBody ChatRequestDto chatRequestDto) {
        ChatClientDto region = chatClientService.getRegion(chatRequestDto.getLongitude(), chatRequestDto.getLatitude());
        return ApiResponseEntity.ok(region);
    }

}
