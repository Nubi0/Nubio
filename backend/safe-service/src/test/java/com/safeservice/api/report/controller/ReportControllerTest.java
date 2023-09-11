package com.safeservice.api.report.controller;

import com.safeservice.ControllerTestSupport;
import com.safeservice.api.report.dto.ReportRequestDto;
import com.safeservice.api.report.dto.ReportUpdateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;


import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import static org.junit.jupiter.api.Assertions.*;

class ReportControllerTest extends ControllerTestSupport {

    @DisplayName("제보 생성 성공 케이스")
    @Test
    void createReport() throws Exception{
        // given
        ReportRequestDto request = ReportRequestDto.builder()
                .title("title")
                .content("content")
                .reportType("terror")
                .longitude(123.13)
                .latitude(36.36).build();
        String requestJson = objectMapper.writeValueAsString(request);
        MockMultipartFile report = new MockMultipartFile("report", "report", "application/json", requestJson.getBytes(StandardCharsets.UTF_8));

        MockMultipartFile multipartFile1 =
                new MockMultipartFile("file", "test.txt",
                        "text/plain", "test file".getBytes(StandardCharsets.UTF_8) );
        // when then
        mockMvc.perform(multipart("/v1/safe/report")
                        .file(multipartFile1)
                        .file(report)
                        .header("Identification", "kim")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("제보 생성 실패 케이스")
    @Test
    void failCreateReport() throws Exception{
        // given
        ReportRequestDto request = ReportRequestDto.builder()
//                .title("title")  // title valid fail
                .content("content")
                .reportType("terror")
                .longitude(123.13)
                .latitude(36.36).build();
        String requestJson = objectMapper.writeValueAsString(request);
        MockMultipartFile report = new MockMultipartFile("report", "report", "application/json", requestJson.getBytes(StandardCharsets.UTF_8));

        MockMultipartFile multipartFile1 =
                new MockMultipartFile("file", "test.txt",
                        "text/plain", "test file".getBytes(StandardCharsets.UTF_8) );
        // when then
        mockMvc.perform(multipart("/v1/safe/report")
                        .file(multipartFile1)
                        .file(report)
                        .header("Identification", "kim")
                )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errorCode").value("400 BAD_REQUEST"))
                .andExpect(jsonPath("$.errorMessage").value("제목은 필수 값 입니다."));
    }

    @DisplayName("제보 수정 성공 케이스")
    @Test
    void updateReport() throws Exception{
        // given
        ReportUpdateRequestDto request = ReportUpdateRequestDto.builder()
                .reportId(1L)
                .title("title")
                .content("content")
                .reportType("terror")
                .longitude(123.13)
                .latitude(36.36).build();
        String requestJson = objectMapper.writeValueAsString(request);
        MockMultipartFile report = new MockMultipartFile("report", "report", "application/json", requestJson.getBytes(StandardCharsets.UTF_8));

        MockMultipartFile multipartFile1 =
                new MockMultipartFile("file", "test.txt",
                        "text/plain", "test file".getBytes(StandardCharsets.UTF_8) );
        // when then

        MockMultipartHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.multipart("/v1/safe/report");
        builder.with(new RequestPostProcessor() {
            @Override
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setMethod("PUT");
                return request;
            }
        });
        mockMvc.perform(builder
                        .file(multipartFile1)
                        .file(report)
                        .header("Identification", "kim"))
                .andExpect(status().isOk());
    }

    @DisplayName("제보 수정 실패 케이스")
    @Test
    void failUpdateReport() throws Exception{
        // given
        ReportUpdateRequestDto request = ReportUpdateRequestDto.builder()
//                .reportId(1L) // reportID valid fail
                .title("title")
                .content("content")
                .reportType("terror")
                .longitude(123.13)
                .latitude(36.36).build();
        String requestJson = objectMapper.writeValueAsString(request);
        MockMultipartFile report = new MockMultipartFile("report", "report", "application/json", requestJson.getBytes(StandardCharsets.UTF_8));

        MockMultipartFile multipartFile1 =
                new MockMultipartFile("file", "test.txt",
                        "text/plain", "test file".getBytes(StandardCharsets.UTF_8) );
        // when then

        MockMultipartHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.multipart("/v1/safe/report");
        builder.with(new RequestPostProcessor() {
            @Override
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setMethod("PUT");
                return request;
            }
        });
        mockMvc.perform(builder
                        .file(multipartFile1)
                        .file(report)
                        .header("Identification", "kim"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errorCode").value("400 BAD_REQUEST"))
                .andExpect(jsonPath("$.errorMessage").value("제보Id는 필수 값 입니다."));
    }

    @DisplayName("제보 조회")
    @Test
    void searchAll() throws Exception {
        // given // when // then
        mockMvc.perform(
                        get("/v1/safe/report")
                                .header("Identification","kim")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }
}