package com.safeservice.domain.emergencymessage.repository;

import com.safeservice.domain.emergencymessage.entity.EmergencyMessage;
import com.safeservice.domain.emergencymessage.entity.type.MdId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EmergencyMessageRepository extends JpaRepository<EmergencyMessage, Long> {

    @Query("SELECT em FROM EmergencyMessage em WHERE em.occurredTime.value >= :fifteenMinutesAgo " +
            "and em.address.city like :city ORDER BY em.occurredTime.value DESC")
    List<EmergencyMessage> findLatestEmergencyMessage(@Param("fifteenMinutesAgo") LocalDateTime fifteenMinutesAgo,
                                                      @Param("city") String city);

    @Query("SELECT em FROM EmergencyMessage em WHERE " +
            "em.address.city like :city ORDER BY em.mdId.value DESC")
    List<EmergencyMessage> findEmergencyMessage(@Param("city") String city);

    boolean existsByMdId(MdId mdId);
}
