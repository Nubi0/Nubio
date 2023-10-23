package com.safeservice.domain.facility.entity;

import com.safeservice.domain.facility.constant.FacilityType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;

@Getter
@NoArgsConstructor
@Document("safety_facility")
public class SafetyFacility {

    @MongoId
    private String id;

    @Field(name = "address")
    private String address;

    @GeoSpatialIndexed(type= GeoSpatialIndexType.GEO_2DSPHERE)
    private Point location;

    @Field(name = "facility_type")
    private FacilityType facilityType;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SafetyFacility that = (SafetyFacility) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Builder
    public SafetyFacility(String id, String address, Point location, FacilityType facilityType){
        this.id = id;
        this.address = address;
        this.location = location;
        this.facilityType = facilityType;
    }

}
