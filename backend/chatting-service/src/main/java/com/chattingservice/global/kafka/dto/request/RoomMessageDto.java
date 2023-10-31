package com.chattingservice.global.kafka.dto.request;

import com.chattingservice.api.chattingroom.dto.response.ChattingRoomResp;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomMessageDto {
    private List<String> receivers;
    private ChattingRoomResp respRoomDto;
}
