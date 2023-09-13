package com.safeservice.domain.facility.constant;

import com.safeservice.global.error.ErrorCode;
import com.safeservice.global.error.exception.BusinessException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum FacilityType {

    SAFETY_BELL("safety_bell"),
    POLICE("police"),
    LAMP("lamp"),
    CONVENIENCE_STORE("convenience_store");

    private final String description;

    FacilityType(String description) {
        this.description = description;
    }


    public static FacilityType from(String type) {
        validateType(type);
        return FacilityType.valueOf(type.toUpperCase());
    }
    public static boolean isFacilityType(String type) {
        List<FacilityType> states = Arrays.stream(FacilityType.values())
                .filter(facility -> facility.name().equals(type))
                .collect(Collectors.toList());

        return states.size() != 0;
    }

    private static void validateType(String type) {
        if(FacilityType.isFacilityType(type.toUpperCase())){
            throw new BusinessException(ErrorCode.FACILITY_TYPE_NOT_EXIST);
        }
    }

}
