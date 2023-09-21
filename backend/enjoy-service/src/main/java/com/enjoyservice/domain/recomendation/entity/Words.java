package com.enjoyservice.domain.recomendation.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@NoArgsConstructor
@Document("words")
public class Words {

    @MongoId
    private String id;

    @Field(name = "pk")
    private Long pk;

    @Field(name = "enjoyList")
    private List<String> enjoyList;

    @Builder
    public Words(String id, Long pk, List<String> enjoyList) {
        this.id = id;
        this.pk = pk;
        this.enjoyList = enjoyList;
    }

    public static Words of(Long pk, List<String> enjoyList) {
        return Words.builder()
                .pk(pk)
                .enjoyList(enjoyList).build();
    }
}
