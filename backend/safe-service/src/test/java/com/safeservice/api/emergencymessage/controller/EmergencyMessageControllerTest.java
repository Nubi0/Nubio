package com.safeservice.api.emergencymessage.controller;

import com.safeservice.ControllerTestSupport;
import com.safeservice.api.emergencymessage.dto.EMAddressDto;
import com.safeservice.api.emergencymessage.dto.EMRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



class EmergencyMessageControllerTest extends ControllerTestSupport {

    @DisplayName("재난문자 정보 생성 성공 케이스")
    @Test
    void createEmergencyMessage() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.now();
        EMRequestDto request = EMRequestDto.builder()
                .mdId(123)
                .emerType("지진")
                .emerStage("안전안내")
                .city("대구광역시")
                .county("달서구")
                .message("지진대피")
                .occurredTime(now).build();
        // when then
        mockMvc.perform(
                post("/v1/safe/emergency")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Identification","kim")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.data").value("생성 완료"));
    }

    @DisplayName("재난문자 정보 조회 성공 케이스")
    @Test
    void emergencyMessage() throws Exception{
        // given
        EMAddressDto request = EMAddressDto.builder()
                .longitude(126.01)
                .latitude(36.01).build();
        // when then
        mockMvc.perform(
                        post("/v1/safe/check")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Identification","kim")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.data").doesNotExist());


    }
}