package com.chattingservice.domain.room.dto.response;

import com.chattingservice.domain.room.dto.LastMessage;
import com.chattingservice.domain.room.entity.Member;
import com.chattingservice.global.kafka.dto.request.RoomType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(description = "방 정보")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RespMyRoomDto {
    private String room_id;
    private RoomType type;
    private String title;
    private List<Member> members;
    private LastMessage last_message;
    private boolean my_room;
}
