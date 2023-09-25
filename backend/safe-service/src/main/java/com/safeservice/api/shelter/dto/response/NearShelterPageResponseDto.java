package com.safeservice.api.shelter.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.safeservice.domain.shelter.entity.Shelter;
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
public class NearShelterPageResponseDto {

    @JsonProperty("content")
    private List<NearShelterResponseDto> content;

    @JsonProperty("meta")
    private PageMetaDto meta;

    static public NearShelterPageResponseDto from(Page<Shelter> shelterPage) {
        return NearShelterPageResponseDto.builder()
                .meta(PageMetaDto.from(shelterPage))
                .content(shelterPage.getContent().stream()
                        .map(shelter -> NearShelterResponseDto.of(shelter))
                        .collect(Collectors.toList())
                ).build();
    }

}
