package com.chattingservice.api.chatting.dto.request;

import com.chattingservice.domain.chatting.entity.MessageCollection;
import com.chattingservice.global.kafka.dto.request.ChatMessageDto;
import com.chattingservice.global.kafka.dto.response.ChatMessageResp;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessagePageDto {

    @JsonProperty("content")
    List<ChatMessageResp> content;

    @JsonProperty("meta")
    private PageMetaDto meta;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PageMetaDto {

        @JsonProperty("page_number")
        private int pageNumber;

        @JsonProperty("page_size")
        private int pageSize;

        @JsonProperty("total_elements")
        private long totalElements;

        @JsonProperty("total_pages")
        private int totalPages;

        @JsonProperty("last_flag")
        private boolean lastFlag;

        static public PageMetaDto from(Page<MessageCollection> chatMessageDtos) {
            return PageMetaDto.builder()
                    .pageNumber(chatMessageDtos.getNumber())
                    .pageSize(chatMessageDtos.getSize())
                    .totalElements(chatMessageDtos.getTotalElements())
                    .totalPages(chatMessageDtos.getTotalPages())
                    .lastFlag(chatMessageDtos.isLast())
                    .build();
        }


    }

    static public ChatMessagePageDto from(Page<MessageCollection> chatMessageDtos) {
        return ChatMessagePageDto.builder()
                .meta(PageMetaDto.from(chatMessageDtos))
                .content(chatMessageDtos.getContent().stream()
                        .map(messageCollection -> ChatMessageResp.from(messageCollection))
                        .collect(Collectors.toList())
                ).build();
    }

}
