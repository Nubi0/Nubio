package com.safeservice.api.path.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safeservice.domain.facility.entity.SafetyFacility;
import com.safeservice.domain.path.entity.Node;
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

    static public PageMetaDto from(Page<Node> nodePage) {
        return PageMetaDto.builder()
                .pageNumber(nodePage.getNumber())
                .pageSize(nodePage.getSize())
                .totalElements(nodePage.getTotalElements())
                .totalPages(nodePage.getTotalPages())
                .lastFlag(nodePage.isLast())
                .build();
    }
}
