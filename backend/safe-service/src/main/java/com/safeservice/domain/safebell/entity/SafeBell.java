package com.safeservice.domain.safebell.entity;

import com.safeservice.domain.safebell.dto.response.NearestSafeBellDto;
import com.safeservice.domain.safebell.type.Active;
import com.safeservice.domain.safebell.type.Address;
import com.safeservice.domain.safebell.type.Position;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@NamedNativeQuery(
        name = "findNearestSafeBell",
        query = "SELECT longitude, latitude, address, ST_Distance_Sphere(POINT(:lng, :lat), POINT(longitude, latitude)) as distance " +
                "FROM safe_bell where active = true order by distance limit 1;",
        resultSetMapping = "NearestSafeBellDtoMapping"
)
@SqlResultSetMapping(
        name = "NearestSafeBellDtoMapping",
        classes = @ConstructorResult(
                targetClass = NearestSafeBellDto.class,
                columns = {
                        @ColumnResult(name = "longitude", type = Double.class),
                        @ColumnResult(name = "latitude", type = Double.class),
                        @ColumnResult(name = "address", type = String.class),
                        @ColumnResult(name = "distance", type = Double.class)
                }
        )
)

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name ="safe_bell")
@SQLDelete(sql="UPDATE safe_bell SET active = false WHERE id = ?")
@Where(clause = "active = true")
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
    public SafeBell(Long id, Position position, Address address) {
        this.id = id;
        this.position = position;
        this.address = address;
    }

}
