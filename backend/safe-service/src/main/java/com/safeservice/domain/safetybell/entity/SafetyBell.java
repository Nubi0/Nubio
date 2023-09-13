package com.safeservice.domain.safetybell.entity;

import org.springframework.data.geo.Point;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@NoArgsConstructor
@Document("safety_bell")
public class SafetyBell {
    @MongoId
    private String id;

    @Field(name = "address")
    private String address;

    @GeoSpatialIndexed(type= GeoSpatialIndexType.GEO_2DSPHERE)
    private Point location;

    @Builder
    public SafetyBell(String id, String address, Point location) {
        this.id = id;
        this.address = address;
        this.location = location;
    }
}

