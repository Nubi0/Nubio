package com.chattingservice.api.chattingroom.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChattingRoomEnterWithProfileReq {
    @NotEmpty
    @JsonProperty("room_id")
    private Long roomId;
    @NotEmpty
    @JsonProperty("nickname")
    private String nickName;
}
