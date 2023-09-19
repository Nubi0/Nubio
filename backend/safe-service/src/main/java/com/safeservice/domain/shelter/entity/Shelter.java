package com.safeservice.domain.shelter.entity;

import com.safeservice.domain.shelter.constant.ShelterType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@NoArgsConstructor
@Document("shelter")
public class Shelter {

    @MongoId
    private String id;

    @Field(name = "address")
    private String address;

    @Field(name = "name")
    private String name;

    @Field(name = "phone_num")
    private String phoneNumber;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Point location;

    @Field(name = "shelter-type")
    private ShelterType shelterType;

    @Builder
    public Shelter(String id, String address, String name, String phoneNumber, Point location, ShelterType shelterType) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.shelterType = shelterType;
    }

}
