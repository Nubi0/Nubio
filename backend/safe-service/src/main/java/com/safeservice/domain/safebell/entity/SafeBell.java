package com.safeservice.domain.safebell.entity;

import com.safeservice.domain.safebell.type.Active;
import com.safeservice.domain.safebell.type.Address;
import com.safeservice.domain.safebell.type.Position;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SafeBell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Position position;

    @Embedded
    private Address address;

    @Embedded
    private Active active = Active.from(true);

    @Builder
    public SafeBell(Long id, Position position, Address address, Active active) {
        this.id = id;
        this.position = position;
        this.address = address;
        this.active = active;
    }

}
