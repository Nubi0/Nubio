package com.authenticationservice.domain.member.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.UUID;

@Embeddable
public class Identification {

    @Column(name = "identification", nullable = false, updatable = false)
    private String value;

    public Identification() {
        this.value = UUID.randomUUID().toString();
    }


    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identification that)) return false;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
