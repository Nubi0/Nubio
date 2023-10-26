package com.chattingservice.domain.chatting.entity;

import com.chattingservice.global.kafka.dto.MessageType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Document(collection = "messages")
public class MessageCollection {
    @MongoId
    private String _id;
    private String roomId;
    private MessageType type;
    private String senderId;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public MessageCollection(MessageType type, String roomId, String senderId, String content, LocalDateTime createdAt) {
        this.type = type;
        this.roomId = roomId;
        this.senderId = senderId;
        this.content = content;
        this.createdAt = createdAt;
    }
}
