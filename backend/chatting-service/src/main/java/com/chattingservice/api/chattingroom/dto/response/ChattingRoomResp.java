package com.chattingservice.api.chattingroom.dto.response;

import com.chattingservice.domain.chattingroom.entity.ChattingRoom;
import com.chattingservice.domain.participant.enity.Participant;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Schema(description = "방 정보")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChattingRoomResp {

    @JsonProperty("room_id")
    private Long roomId;
    @JsonProperty("title")
    private String title;
    @Schema(description = "1:1 - DM, 그룹 - GROUP")
    @JsonProperty("room_type")
    private String roomType;
    @JsonProperty("profile_url")
    private String profileUrl;
    @JsonProperty("members")
    private List<Member> members;
    @JsonProperty("sido_name")
    private String sidoName;
    @JsonProperty("sgg_name")
    private String sggName;
    @JsonProperty("umd_name")
    private String umdName;
    @JsonProperty("ri_name")
    private String riName;
    @JsonProperty("exist_member_num")
    private Long existMemberNum;
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Member {
        @JsonProperty("id")
        private Long id;
        @JsonProperty("member_id")
        private String memberId;
        @JsonProperty("profile_url")
        private String profileUrl;
        @JsonProperty("last_read_msg_id")
        private String lastReadMsgId;
        @JsonProperty("active")
        private boolean active;
        @JsonProperty("role")
        private String role;
        @JsonProperty("nickname")
        private String nickname;

        public static Member from(Participant participant) {
            return Member.builder()
                    .id(participant.getId())
                    .memberId(participant.getMemberId())
                    .profileUrl(participant.getProfile().getFileUrl().getValue())
                    .lastReadMsgId(participant.getLastReadMsgId())
                    .active(participant.getActive().getValue())
                    .role(participant.getRole().name())
                    .nickname(participant.getNickname().getValue())
                    .build();
        }

    }

    public static ChattingRoomResp form(ChattingRoom chattingRoom) {
        AtomicLong existMemberNum = new AtomicLong(0);
        return ChattingRoomResp.builder()
                .roomId(chattingRoom.getId())
                .title(chattingRoom.getTitle().getValue())
                .roomType(chattingRoom.getRoomType().name())
                .profileUrl(chattingRoom.getProfile().getFileUrl().getValue())
                .sidoName(chattingRoom.getSidoName().getName())
                .sggName(chattingRoom.getSggName().getName())
                .umdName(chattingRoom.getUmdName().getName())
                .riName(chattingRoom.getRiName().getName())
                .members(chattingRoom.getParticipants().stream()
                        .map(participant -> {
                            if (participant.getActive().getValue()==true) {
                                existMemberNum.incrementAndGet();
                            }
                            return Member.from(participant);
                        })
                        .collect(Collectors.toList())
                )
                .existMemberNum(existMemberNum.get())
                .build();

    }
}
