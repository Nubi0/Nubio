package com.safeservice.api.emergencymessage.service;

import com.safeservice.api.emergencymessage.dto.EMAddressDto;
import com.safeservice.api.emergencymessage.dto.EMReq;
import com.safeservice.api.emergencymessage.dto.EMRequestDto;
import com.safeservice.api.emergencymessage.dto.EMResponseDto;
import com.safeservice.api.report.dto.ReportRequestDto;
import com.safeservice.domain.emergencymessage.repository.EmergencyMessageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EmergencyMessageInfoServiceTest {

    @Autowired
    private EmergencyMessageInfoService emergencyMessageInfoService;

    @Autowired
    private EmergencyMessageRepository emergencyMessageRepository;

    static final int emergencyMessageLength = 5;

    @BeforeEach
    void before() {
        LocalDateTime occurredTime = LocalDateTime.now();
        for (int i = 0; i < emergencyMessageLength; i++) {
            EMRequestDto request = EMRequestDto.builder()
                    .mdId(123)
                    .emerType("지진")
                    .emerStage("안전안내")
                    .city("대구광역시")
                    .county("달서구")
                    .message("지진대피")
                    .occurredTime(occurredTime).build();
            emergencyMessageRepository.save(EMRequestDto.toEntity(request));
        }
    }

    @AfterEach
    void after() {
        emergencyMessageRepository.deleteAll();
    }

    @DisplayName("재난문자 생성 성공 케이스")
    @Test
    void createEmergencyMessage() {
        // given
        LocalDateTime now = LocalDateTime.now();

        List<EMRequestDto> data = new ArrayList<>();
        EMRequestDto req = EMRequestDto.builder()
                .mdId(123)
                .emerType("지진")
                .emerStage("안전안내")
                .city("대구광역시")
                .county("달서구")
                .message("지진대피")
                .occurredTime(now).build();
        data.add(req);
        EMReq request = EMReq.from(data);
        List<MultipartFile> files = new ArrayList<>();
        MockMultipartFile file =
                new MockMultipartFile("file", "test.png",
                        "image/png", "test file".getBytes(StandardCharsets.UTF_8));
        files.add(file);
        // when then
        assertThatCode(() -> emergencyMessageInfoService.createEmergencyMessage(request))
                .doesNotThrowAnyException();
    }

    @DisplayName("재난문자 조회 성공 케이스")
    @Test
    void checkEM() {
        // given
        double DAUGU_longitude = 128.6419;
        double DAUGU_latitude = 35.8523;
        EMAddressDto request = EMAddressDto.builder()
                .longitude(DAUGU_longitude)
                .latitude(DAUGU_latitude).build();

        // when
        EMResponseDto emResponseDto = emergencyMessageInfoService.checkEM(request);

        // then
        assertThat(emResponseDto.getEmergencyMessages().size()).isEqualTo(emergencyMessageLength);
        assertThat(emResponseDto.isEmergencyMessageFlag()).isEqualTo(true);
    }
}