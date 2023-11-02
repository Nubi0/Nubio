package com.chattingservice.domain.chatting.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatFileDto {
    private String url;

    public ChatFileDto(String url) {
        this.url = url;
    }

}
