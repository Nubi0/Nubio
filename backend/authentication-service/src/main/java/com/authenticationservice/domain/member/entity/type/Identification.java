package com.authenticationservice.domain.member.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Embeddable
public class Identification {
    @Column(name = "identification", nullable = false, updatable = false)
    private String value;

    protected Identification(){}

    private Identification(final String value) {
        this.value = value;
    }

    public static Identification createIdentification() {
        return from(UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }

    public static Identification from(final String value) {
        return new Identification(value);
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
