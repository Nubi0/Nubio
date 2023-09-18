package com.safeservice.domain.emergencymessage.entity;


import com.safeservice.domain.common.BaseTimeEntity;
import com.safeservice.domain.emergencymessage.entity.constant.EmerStage;
import com.safeservice.domain.emergencymessage.entity.constant.EmerType;
import com.safeservice.domain.emergencymessage.entity.type.Address;
import com.safeservice.domain.emergencymessage.entity.type.MdId;
import com.safeservice.domain.emergencymessage.entity.type.Message;
import com.safeservice.domain.emergencymessage.entity.type.OccurredTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "emergency_message")
public class EmergencyMessage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private MdId mdId;

    @Enumerated(EnumType.STRING)
    private EmerType emerType;

    @Enumerated(EnumType.STRING)
    private EmerStage emerStage;

    @Embedded
    private Address address;

    @Embedded
    private Message message;

    @Embedded
    private OccurredTime occurredTime;

    @Builder
    public EmergencyMessage(MdId mdId, EmerType emerType, EmerStage emerStage, Address address, Message message, OccurredTime occurredTime) {
        this.mdId = mdId;
        this.emerType = emerType;
        this.emerStage = emerStage;
        this.address = address;
        this.message = message;
        this.occurredTime = occurredTime;
    }
}
