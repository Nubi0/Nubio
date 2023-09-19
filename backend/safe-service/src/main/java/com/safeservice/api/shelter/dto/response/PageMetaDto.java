package com.safeservice.api.shelter.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safeservice.domain.facility.entity.SafetyFacility;
import com.safeservice.domain.shelter.entity.Shelter;
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

    static public PageMetaDto from(Page<Shelter> shelterPage) {
        return PageMetaDto.builder()
                .pageNumber(shelterPage.getNumber())
                .pageSize(shelterPage.getSize())
                .totalElements(shelterPage.getTotalElements())
                .totalPages(shelterPage.getTotalPages())
                .lastFlag(shelterPage.isLast())
                .build();
    }

}
