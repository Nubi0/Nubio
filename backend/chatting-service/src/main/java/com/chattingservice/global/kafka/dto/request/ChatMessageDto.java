package com.chattingservice.global.kafka.dto.request;

import com.chattingservice.domain.chatting.entity.MessageCollection;
import com.chattingservice.domain.chatting.entity.constant.MessageType;
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
public class ChatMessageDto {
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
    @Schema(description = "내용")
    @JsonProperty("content")
    private String content;
    @JsonProperty("message_id")
    private String message_id;
    @JsonProperty("created_at")
    private LocalDateTime created_at;
    @JsonProperty("file")
    private List<MultipartFile> files;
    @Builder
    public ChatMessageDto(String message_id, MessageType message_type
            , String room_id, String sender_id, String content
            ,LocalDateTime created_at,List<MultipartFile> files) {
        this.message_id = message_id;
        this.message_type = message_type;
        this.room_id = room_id;
        this.sender_id = sender_id;
        this.content = content;
        this.created_at = created_at;
        this.files = files;
    }


    public static ChatMessageDto from(MessageCollection messageCollection) {
        return ChatMessageDto.builder()
                .room_id(messageCollection.getRoomId())
                .message_type(messageCollection.getType())
                .sender_id(messageCollection.getSenderId())
                .content(messageCollection.getContent())
                .created_at(messageCollection.getCreatedAt())
                .message_id(messageCollection.get_id())
                .build();
    }

}
