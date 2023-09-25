package com.enjoyservice.api.taste.dto.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberTasteReq {

    @JsonProperty("taste")
    private List<MemberTasteInfo> taste;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static public class MemberTasteInfo {

        @JsonProperty("type")
        private String type;

        @JsonProperty("detail_type")
        private List<String> detailType;
    }
}
