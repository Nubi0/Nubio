package com.chattingservice.api.chattingroom.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChattingRoomOutReq {
    @JsonProperty("room_id")
    private Long roomId;
    @JsonProperty("member_id")
    private String memberId;
}
