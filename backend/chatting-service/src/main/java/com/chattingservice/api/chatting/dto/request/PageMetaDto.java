package com.chattingservice.api.chatting.dto.request;

import com.chattingservice.domain.chatting.entity.MessageCollection;
import com.chattingservice.global.kafka.dto.request.ChatMessageDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageMetaDto {

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
