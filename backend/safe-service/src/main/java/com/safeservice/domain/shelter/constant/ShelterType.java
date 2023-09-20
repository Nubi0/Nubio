package com.safeservice.domain.shelter.constant;

import com.safeservice.global.error.ErrorCode;
import com.safeservice.global.error.exception.BusinessException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum ShelterType {

    SCHOOL("school"),
    SENIOR_WELFARE("senior welfare"),
    CIVIL_DEFENSE("civil_defense"),
    HOSPITAL("hospital"),
    FIRE_STATION("fire_station"),
    SUBWAY("subway")
    ;

    private final String description;

    ShelterType(String description) {
        this.description = description;
    }

    public static ShelterType from(String type) {
        validateType(type);
        return ShelterType.valueOf(type.toUpperCase());
    }

    public static boolean isShelterType(String type) {
        List<ShelterType> shelterTypes = Arrays.stream(ShelterType.values())
                .filter(shelterType -> shelterType.name().equals(type))
                .collect(Collectors.toList());
        return shelterTypes.size() != 0;
    }
    private static void validateType(String type) {
        if (ShelterType.isShelterType(type.toUpperCase())) {
            throw new BusinessException(ErrorCode.SHELTER_TYPE_NOT_EXIST);
        }
    }


}
