package com.chattingservice.global.kafka.dto.response;


import com.chattingservice.domain.chatting.entity.MessageCollection;
import com.chattingservice.domain.chatting.entity.constant.MessageType;
import com.chattingservice.global.kafka.dto.request.ChatMessageDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "채팅메시지")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ChatMessageResp {
    @Schema(description = "메시지 타입")
    @JsonProperty("message_type")
    private MessageType message_type;
    @NotEmpty
    @Schema(description = "채팅방 id")
    @JsonProperty("room_id")
    private String room_id;
    @NotEmpty
    @Schema(description = "보낸 유저 id")
    @JsonProperty("sender_id")
    private String sender_id;
    @NotBlank
    @Schema(description = "내용")
    @JsonProperty("content")
    private String content;
    @JsonProperty("message_id")
    private String message_id;
    @JsonProperty("created_at")
    private LocalDateTime created_at;
    @JsonProperty("origin_url")
    private List<String> originUrl;
    @JsonProperty("resize_url")
    private List<String> resizeUrl;
    @Builder
    public ChatMessageResp(String message_id, MessageType message_type
            , String room_id, String sender_id, String content
            ,LocalDateTime created_at,List<String> originUrl,List<String> resizeUrl) {
        this.message_id = message_id;
        this.message_type = message_type;
        this.room_id = room_id;
        this.sender_id = sender_id;
        this.content = content;
        this.created_at = created_at;
        this.originUrl = originUrl;
        this.resizeUrl = resizeUrl;
    }


    public static ChatMessageResp from(MessageCollection messageCollection) {
        return ChatMessageResp.builder()
                .room_id(messageCollection.getRoomId())
                .message_type(messageCollection.getType())
                .sender_id(messageCollection.getSenderId())
                .content(messageCollection.getContent())
                .created_at(messageCollection.getCreatedAt())
                .message_id(messageCollection.get_id())
                .originUrl(messageCollection.getOriginImgUrl())
                .resizeUrl(messageCollection.getResizeImgUrl())
                .build();
    }

}
