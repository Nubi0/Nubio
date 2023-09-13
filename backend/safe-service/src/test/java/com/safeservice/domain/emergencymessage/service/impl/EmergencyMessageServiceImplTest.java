package com.safeservice.domain.emergencymessage.service.impl;

import com.safeservice.domain.emergencymessage.entity.EmergencyMessage;
import com.safeservice.domain.emergencymessage.entity.constant.EmerStage;
import com.safeservice.domain.emergencymessage.entity.constant.EmerType;
import com.safeservice.domain.emergencymessage.entity.type.Address;
import com.safeservice.domain.emergencymessage.entity.type.MdId;
import com.safeservice.domain.emergencymessage.entity.type.Message;
import com.safeservice.domain.emergencymessage.entity.type.OccurredTime;
import com.safeservice.domain.emergencymessage.exception.InvalidEmerStageException;
import com.safeservice.domain.emergencymessage.exception.InvalidEmerTypeException;
import com.safeservice.domain.emergencymessage.repository.EmergencyMessageRepository;
import com.safeservice.domain.emergencymessage.service.EmergencyMessageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class EmergencyMessageServiceImplTest {

    @Autowired
    private EmergencyMessageService emergencyMessageService;

    @Autowired
    private EmergencyMessageRepository emergencyMessageRepository;

    @AfterEach
    void after() {
        emergencyMessageRepository.deleteAll();
    }


    @DisplayName("재난문자 저장 성공 케이스")
    @Test
    void save() {
        // given
        EmergencyMessage originEM = EmergencyMessage.builder()
                .mdId(MdId.from(123))
                .emerStage(EmerStage.from("안전안내"))
                .emerType(EmerType.from("지진"))
                .address(Address.of("대구시", "달서구"))
                .message(Message.from("message"))
                .occurredTime(OccurredTime.from
                        (LocalDateTime.of(2023, 9, 13, 12, 12, 12)))
                .build();

        // when
        EmergencyMessage savedEM = emergencyMessageService.save(originEM);

        // then
        assertThat(originEM).isEqualTo(savedEM);
        assertThat(originEM.getMdId().getValue()).isEqualTo(savedEM.getMdId().getValue());
        assertThat(originEM.getEmerType().getDescription()).isEqualTo(savedEM.getEmerType().getDescription());
        assertThat(originEM.getEmerStage().getDescription()).isEqualTo(savedEM.getEmerStage().getDescription());
        assertThat(originEM.getAddress().getCity()).isEqualTo(savedEM.getAddress().getCity());
        assertThat(originEM.getAddress().getCounty()).isEqualTo(savedEM.getAddress().getCounty());
        assertThat(originEM.getMessage().getValue()).isEqualTo(savedEM.getMessage().getValue());
        assertThat(originEM.getOccurredTime().getValue()).isEqualTo(savedEM.getOccurredTime().getValue());
    }


    @DisplayName("재난문자 15분전 송신여부 성공 케이스")
    @ParameterizedTest
    @ValueSource(ints = {2, 5, 10, 14})
    void searchLatestEM(int length) {
        // given
        int occurredTime = 30;
        int fifteenMinutesAgo = occurredTime - length;
        for (int i = 0; i < length; i++) {
            EmergencyMessage em = EmergencyMessage.builder()
                    .mdId(MdId.from(i))
                    .emerStage(EmerStage.from("안전안내"))
                    .emerType(EmerType.from("지진"))
                    .address(Address.of("대구시", "달서구"))
                    .message(Message.from("message"))
                    .occurredTime(OccurredTime.from
                            (LocalDateTime.of(2023, 9, 13, 2, occurredTime, 30)))
                    .build();
            emergencyMessageRepository.save(em);
        }
        LocalDateTime checkTime = LocalDateTime.of(2023, 9, 13, 2, fifteenMinutesAgo, 30);

        // when
        List<EmergencyMessage> emergencyMessages = emergencyMessageService.searchLatestEM(checkTime, "대구시");

        // then
        assertThat(emergencyMessages.size()).isEqualTo(length);
    }

    @DisplayName("재난문자 타입 오류 실패 케이스")
    @Test
    void typeFailSave() {
        // given
        String wrongStr = "잘못된 입력";

        // when then
        assertThatThrownBy(() -> EmerType.from(wrongStr))
                .isInstanceOf(InvalidEmerTypeException.class);
    }

    @DisplayName("재난문자 타입 오류 실패 케이스")
    @Test
    void stageFailSave() {
        // given
        String wrongStr = "잘못된 입력";

        // when then
        assertThatThrownBy(() -> EmerStage.from(wrongStr))
                .isInstanceOf(InvalidEmerStageException.class);
    }
}