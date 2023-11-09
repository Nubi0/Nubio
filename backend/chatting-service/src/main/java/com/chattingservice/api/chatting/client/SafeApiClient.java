package com.chattingservice.api.chatting.client;

import com.chattingservice.api.ApiResponseEntity;
import com.chattingservice.api.chatting.client.dto.request.ChatRequestDto;
import com.chattingservice.api.chatting.client.dto.response.ChatClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "http://ec2-13-124-94-38.ap-northeast-2.compute.amazonaws.com:8082", name = "SafeApiClient")
public interface SafeApiClient {

    @PostMapping(consumes = "application/json"
            ,value = "/v1/safe/chat")
    ApiResponseEntity<ChatClientDto> requestDataApi(ChatRequestDto chatRequestDto);
}
