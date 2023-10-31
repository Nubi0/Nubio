package com.chattingservice.domain.room.dto.response;

import com.chattingservice.domain.room.dto.LastMessage;
import com.chattingservice.domain.room.entity.Member;
import com.chattingservice.global.kafka.dto.request.RoomType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RespMyChatRoom {
    private String room_id;
    private RoomType type;
    private String title;
    private List<Member> members;
    private LastMessage last_message;
}
