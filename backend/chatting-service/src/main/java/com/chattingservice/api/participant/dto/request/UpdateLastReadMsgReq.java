package com.chattingservice.api.participant.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLastReadMsgReq {
    @JsonProperty("room_id")
    private Long roomId;
    @JsonProperty("last_read_msg")
    private String lastReadMsg;
}
