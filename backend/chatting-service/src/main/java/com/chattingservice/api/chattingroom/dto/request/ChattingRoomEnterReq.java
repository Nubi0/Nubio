package com.chattingservice.api.chattingroom.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChattingRoomEnterReq {
    @JsonProperty("room_id")
    private Long roomId;
    @JsonProperty("member_id")
    private String memberId;
}
