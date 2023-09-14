package com.enjoyservice.domain.course.entity.constant;

import com.enjoyservice.domain.course.exception.InvalidRegionException;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.exception.InvalidGroupNameException;
import com.enjoyservice.global.error.ErrorCode;

import java.util.Arrays;
import java.util.List;

public enum Region {

    DAEGU("대구"),
    ;

    private String description;

    Region(String description) {
        this.description = description;
    }

    public static Region from(String region) {
        validate(region);
        return Region.valueOf(region.toUpperCase());
    }

    public static boolean isRegion(String region) {
        List<Region> regions = Arrays.stream(Region.values())
                .filter(g -> g.name().equals(region))
                .toList();

        return regions.size() != 0;
    }

    private static void validate(String region) {
        if(!Region.isRegion(region.toUpperCase())) {
            throw new InvalidRegionException(ErrorCode.INVALID_REGION);
        }
    }
}
