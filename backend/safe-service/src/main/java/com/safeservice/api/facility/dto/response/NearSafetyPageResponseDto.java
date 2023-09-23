package com.safeservice.api.facility.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safeservice.domain.facility.entity.SafetyFacility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearSafetyPageResponseDto {

    @JsonProperty("content")
    private List<NearSafetyResponseDto> content;

    @JsonProperty("meta")
    private PageMetaDto meta;

    static public NearSafetyPageResponseDto from(Page<SafetyFacility> safetyFacilityPage) {
         return  NearSafetyPageResponseDto.builder()
                 .meta(PageMetaDto.from(safetyFacilityPage))
                 .content(safetyFacilityPage.getContent().stream()
                         .map(safetyFacility -> NearSafetyResponseDto.of(safetyFacility))
                         .collect(Collectors.toList())
                 ).build();
    }


}
