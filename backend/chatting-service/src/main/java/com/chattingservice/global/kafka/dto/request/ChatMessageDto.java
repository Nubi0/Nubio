package com.chattingservice.global.kafka.dto.request;

import com.chattingservice.global.kafka.dto.MessageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

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
    @NotBlank
    @Schema(description = "내용")
    @JsonProperty("content")
    private String content;
    @JsonProperty("message_id")
    private String message_id;
    @JsonProperty("created_at")
    private LocalDateTime created_at;

    @Builder
    public ChatMessageDto(String message_id, MessageType message_type, String room_id, String sender_id, String content,LocalDateTime created_at) {
        this.message_id = message_id;
        this.message_type = message_type;
        this.room_id = room_id;
        this.sender_id = sender_id;
        this.content = content;
        this.created_at = created_at;
    }
}
