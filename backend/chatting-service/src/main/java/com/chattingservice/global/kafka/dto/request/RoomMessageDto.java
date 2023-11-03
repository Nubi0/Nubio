package com.chattingservice.global.kafka.dto.request;

import com.chattingservice.api.chattingroom.dto.response.ChattingRoomResp;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomMessageDto {
    @JsonProperty("members")
    private List<String> members;

    @JsonProperty("resp_room_dto")
    private ChattingRoomResp respRoomDto;
}
