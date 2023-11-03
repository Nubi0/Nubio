package com.chattingservice.api.chattingroom.dto.response;

import com.chattingservice.api.chatting.dto.request.ChatMessagePageDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "방, 메시지 정보")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChattingRoomEnterWithProfileResp {
    @JsonProperty("chatting_room")
    private ChattingRoomResp chattingRoomResp;
    @JsonProperty("chat_message_page")
    private ChatMessagePageDto chatMessagePageDto;

    public static ChattingRoomEnterWithProfileResp of(ChattingRoomResp chattingRoomResp, ChatMessagePageDto chatMessagePageDto) {
        return ChattingRoomEnterWithProfileResp.builder()
                .chattingRoomResp(chattingRoomResp)
                .chatMessagePageDto(chatMessagePageDto)
                .build();
    }

}
