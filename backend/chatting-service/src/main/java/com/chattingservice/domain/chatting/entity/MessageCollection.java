package com.chattingservice.domain.chatting.entity;

import com.chattingservice.domain.chatting.entity.constant.MessageType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private List<String> originImgUrl = new ArrayList<>();
    private List<String> resizeImgUrl = new ArrayList<>();


    @Builder
    public MessageCollection(MessageType type, String roomId, String senderId, String content
            , LocalDateTime createdAt) {
        this.type = type;
        this.roomId = roomId;
        this.senderId = senderId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public void updateOriginImgUrl(List<String> originImgUrl) {
        this.originImgUrl = originImgUrl;
    }

    public void updateResizeImgUrl(List<String> resizeImgUrl) {
        this.resizeImgUrl = resizeImgUrl;
    }
}
