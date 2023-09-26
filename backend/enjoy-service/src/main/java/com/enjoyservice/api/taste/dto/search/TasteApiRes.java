package com.enjoyservice.api.taste.dto.search;

import com.enjoyservice.api.taste.dto.update.MemberTasteReq;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TasteApiRes {

    @JsonProperty("taste")
    private List<MemberTasteInfoDto> taste = new ArrayList<>();

    public static TasteApiRes of(MemberTasteInfoDto eat, MemberTasteInfoDto drink, MemberTasteInfoDto play) {
        TasteApiRes tasteApiRes = new TasteApiRes();
        tasteApiRes.add(eat);
        tasteApiRes.add(drink);
        tasteApiRes.add(play);
        return tasteApiRes;
    }

    public void add(MemberTasteInfoDto taste) {
        this.taste.add(taste);
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static public class MemberTasteInfoDto {

        @JsonProperty("type")
        private String type;

        @JsonProperty("detail_type")
        private List<String> detailType;

        public static MemberTasteInfoDto of(String type, List<String> detailType) {
            return MemberTasteInfoDto.builder()
                    .type(type)
                    .detailType(detailType).build();
        }
    }
}
