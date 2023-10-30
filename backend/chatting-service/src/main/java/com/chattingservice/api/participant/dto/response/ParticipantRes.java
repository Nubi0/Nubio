package com.chattingservice.api.participant.dto.response;

import com.chattingservice.domain.participant.enity.Participant;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantRes {

    @JsonProperty("member_id")
    private String memberId;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("profile_url")
    private String profileUrl;
    @JsonProperty("role")
    private String role;
    @JsonProperty("last_read_msg_id")
    private String lastReadMsgId;

    public ParticipantRes of(Participant participant) {
        return ParticipantRes.builder()
                .memberId(participant.getMemberId())
                .nickname(participant.getNickname().getValue())
                .profileUrl(participant.getProfile().getFileUrl().getValue())
                .role(participant.getRole().name())
                .lastReadMsgId(participant.getLastReadMsgId())
                .build();
    }

}
