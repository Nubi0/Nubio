package com.chattingservice.api.chattingroom.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChattingRoomSearchReq {
    @JsonProperty("sido_name")
    private String sidoName;
    @JsonProperty("ssg_name")
    private String sggName;
    @JsonProperty("umd_name")
    private String umdName;
    @JsonProperty("ri_name")
    private String riName;
}
