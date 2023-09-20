package com.safeservice.api.path.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safeservice.api.facility.dto.response.NearSafetyPageResponseDto;
import com.safeservice.domain.path.entity.Node;
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
public class NearNodePageResponse {

    @JsonProperty("content")
    private List<NearNodeResponse> content;

    @JsonProperty("meta")
    private PageMetaDto meta;

    static public NearNodePageResponse from(Page<Node> nodePage) {
        return NearNodePageResponse.builder()
                .meta(PageMetaDto.from(nodePage))
                .content(nodePage.getContent().stream()
                        .map(node -> NearNodeResponse.of(node))
                        .collect(Collectors.toList())
                ).build();
    }

}
