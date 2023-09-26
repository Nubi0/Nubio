package com.enjoyservice.api.taste.dto.create;

import com.enjoyservice.domain.taste.entity.Taste;
import com.enjoyservice.domain.taste.entity.constant.DetailType;
import com.enjoyservice.domain.taste.entity.constant.Type;
import com.enjoyservice.domain.taste.entity.type.Active;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TasteInfoReq {

    @JsonProperty("type")
    private String type;

    @JsonProperty("detail_type")
    private String detailType;

    public static Taste toEntity(TasteInfoReq tasteInfoReq) {
        return Taste.builder()
                .active(Active.from(true))
                .type(Type.from(tasteInfoReq.getType()))
                .detailType(DetailType.from(tasteInfoReq.getDetailType())).build();
    }
}
