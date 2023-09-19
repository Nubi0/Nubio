package com.safeservice.domain.path.entity;

import com.safeservice.domain.facility.constant.FacilityType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@NoArgsConstructor
@Document("node")
public class Node {
    @MongoId
    private String id;

    @GeoSpatialIndexed(type= GeoSpatialIndexType.GEO_2DSPHERE)
    private Point location;

    /*
    // 안전 시설 점수
    경찰서 : 10
    편의점 : 5
    안전벨 : 1
    가로등 : 1
     */
    @Indexed
    @Field(name = "safety_score")
    private Integer safety_score;

    @Builder
    public Node(String id, Point location, Integer safety_score){
        this.id = id;
        this.location = location;
        this.safety_score = safety_score;
    }

}
