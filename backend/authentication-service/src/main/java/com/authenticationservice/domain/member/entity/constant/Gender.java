package com.authenticationservice.domain.member.entity.constant;

import com.authenticationservice.domain.member.exception.InvalidGenderException;
import com.authenticationservice.global.error.ErrorCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Gender {

    MALE("male"),
    FEMALE("female")
    ;

    private String description;

    Gender(String description) {
        this.description = description;
    }

    public static Gender from(String gender) {
        validate(gender);
        return Gender.valueOf(gender.toUpperCase());
    }

    public static boolean isRole(String gender) {
        List<Gender> genders = Arrays.stream(Gender.values())
                .filter(g -> g.name().equals(gender))
                .collect(Collectors.toList());

        return genders.size() != 0;
    }

    private static void validate(String gender) {
        if(!Gender.isRole(gender.toUpperCase())) {
            throw new InvalidGenderException(ErrorCode.INVALID_GENDER);
        }
    }
}
