package com.chattingservice.api.chattingroom.dto.response;

import com.chattingservice.api.chatting.dto.request.ChatMessagePageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "방, 메시지 정보")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChattingRoomEnterWithProfileResp {

    private ChattingRoomResp chattingRoomResp;
    private ChatMessagePageDto chatMessagePageDto;

    public static ChattingRoomEnterWithProfileResp of(ChattingRoomResp chattingRoomResp, ChatMessagePageDto chatMessagePageDto) {
        return ChattingRoomEnterWithProfileResp.builder()
                .chattingRoomResp(chattingRoomResp)
                .chatMessagePageDto(chatMessagePageDto)
                .build();
    }

}
