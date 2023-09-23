package com.safeservice.domain.path.constant;

import com.safeservice.domain.facility.constant.FacilityType;
import lombok.Getter;

@Getter
public enum FacilityScore {

    POLICE(FacilityType.POLICE, 10),
    CONVENIENCE_STORE(FacilityType.CONVENIENCE_STORE, 5),
    SAFETY_BELL(FacilityType.SAFETY_BELL, 1),
    LAMP(FacilityType.LAMP, 1),
    ;

    private final FacilityType facilityType;
    private final Integer score;

    FacilityScore(FacilityType facilityType, Integer score) {
        this.facilityType = facilityType;
        this.score = score;
    }

    public static FacilityScore from(FacilityType facilityType) {
        return FacilityScore.valueOf(facilityType.name());
    }
}
