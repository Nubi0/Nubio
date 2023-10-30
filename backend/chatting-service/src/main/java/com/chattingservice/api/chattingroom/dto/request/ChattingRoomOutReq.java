package com.chattingservice.api.chattingroom.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChattingRoomOutReq {
    @JsonProperty("room_id")
    private Long roomId;
    @JsonProperty("member_id")
    private String memberId;
}
