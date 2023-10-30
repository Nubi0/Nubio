package com.chattingservice.global.kafka.dto.request;

import com.chattingservice.domain.room.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "방생성 정보")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RespRoomDto {
    private String room_id;
    private String title;
    @Schema(description = "1:1 - DM, 그룹 - GROUP")
    private RoomType type;
    private List<Member> members;
    private List<String> managers;
    private LocalDateTime createdAt;
}
