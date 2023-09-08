package com.authenticationservice.domain.member.entity.constant;

import com.authenticationservice.domain.member.exception.InvalidRoleException;
import com.authenticationservice.global.error.ErrorCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Role {

    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN")
    ;

    private String description;

    Role(String description) {
        this.description = description;
    }

    public static Role from(String role) {
        validate(role);
        return Role.valueOf(role.toUpperCase());
    }

    public static boolean isRole(String role) {
        List<Role> roles = Arrays.stream(Role.values())
                .filter(r -> r.name().equals(role))
                .collect(Collectors.toList());

        return roles.size() != 0;
    }

    private static void validate(String role) {
        if(!Role.isRole(role.toUpperCase())) {
            throw new InvalidRoleException(ErrorCode.INVALID_ROLE);
        }
    }
}
