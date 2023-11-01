package com.chattingservice.api.chattingroom.dto.request;

import com.chattingservice.api.chatting.client.dto.response.ChatClientDto;
import com.chattingservice.api.chattingroom.dto.response.ChattingRoomResp;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindLocationReq {

    @JsonProperty("chat_client")
    private ChatClientDto chatClientDto;

    @JsonProperty("chatting_room")
    private ChattingRoomResp chattingRoomResp;

}
