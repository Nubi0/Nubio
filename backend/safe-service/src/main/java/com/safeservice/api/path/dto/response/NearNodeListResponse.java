package com.safeservice.api.path.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safeservice.domain.path.entity.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearNodeListResponse {

    @JsonProperty("content")
    private List<NearNodeResponse> content;

    static public NearNodeListResponse from(List<Node> nodeList) {
        return NearNodeListResponse.builder()
                .content(nodeList.stream()
                        .map(node -> NearNodeResponse.of(node))
                        .collect(Collectors.toList())
                ).build();
    }


}
