package com.safeservice.api.report.service;

import com.safeservice.api.report.dto.ReportRequestDto;
import com.safeservice.api.report.dto.ReportResponseDto;
import com.safeservice.api.report.dto.ReportUpdateRequestDto;
import com.safeservice.domain.report.entity.Report;
import com.safeservice.domain.report.entity.constant.report.ReportType;
import com.safeservice.domain.report.entity.type.report.Active;
import com.safeservice.domain.report.entity.type.report.Content;
import com.safeservice.domain.report.entity.type.report.Position;
import com.safeservice.domain.report.entity.type.report.Title;
import com.safeservice.domain.report.exception.InvalidTitleLengthException;
import com.safeservice.domain.report.exception.MisMatchIdentification;
import com.safeservice.domain.report.repository.ReportRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReportInfoServiceTest {

    @Autowired
    private ReportInfoService reportInfoService;

    @Autowired
    private ReportRepository reportRepository;

    private Report firstReport;
    private Report beforeReport;

    @BeforeEach
    void before() {
        firstReport = Report.builder()
                .reportType(ReportType.TERROR)
                .title(Title.from("first"))
                .content(Content.from("first content"))
                .position(Position.of(126.36, 36.36))
                .identification("kim")
                .active(Active.from(true)).build();
        beforeReport = reportRepository.save(firstReport);
    }

    @AfterEach
    void after() {
        reportRepository.deleteAll();
    }


    @Test
    @DisplayName("제보 생성 케이스")
    void createReport() throws Exception{
        // given
        ReportRequestDto request = ReportRequestDto.builder()
                .title("title")
                .content("content")
                .reportType("terror")
                .longitude(123.13)
                .latitude(36.36).build();
        List<MultipartFile> files = new ArrayList<>();
        MockMultipartFile file =
                new MockMultipartFile("file", "test.txt",
                        "text/plain", "test file".getBytes(StandardCharsets.UTF_8));
        files.add(file);
        // when then
        assertThatCode(() -> reportInfoService.createReport(request,files,"kim"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("제보 수정 성공 케이스")
    void updateReport() {
        String changeTitle = "title";
        String changeContent = "content";
        String changeReportType = "accident";
        String checkIdentification = "kim";

        ReportUpdateRequestDto request = ReportUpdateRequestDto.builder()
                .reportId(beforeReport.getId())
                .title(changeTitle)
                .content(changeContent)
                .reportType(changeReportType)
                .longitude(123.13)
                .latitude(36.36).build();
        List<MultipartFile> files = new ArrayList<>();
        // when then
        assertThatCode(() -> reportInfoService.updateReport(request,files,checkIdentification))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("제보 수정 실패 케이스")
    void failUpdateReport() {
        String changeTitle = "title";
        String changeContent = "content";
        String changeReportType = "accident";
        String failIdentification = "fail identification";

        ReportUpdateRequestDto request = ReportUpdateRequestDto.builder()
                .reportId(beforeReport.getId())
                .title(changeTitle)
                .content(changeContent)
                .reportType(changeReportType)
                .longitude(123.13)
                .latitude(36.36).build();
        List<MultipartFile> files = new ArrayList<>();
        // when then
        assertThatThrownBy(() -> reportInfoService.updateReport(request,files,failIdentification))
                .isInstanceOf(MisMatchIdentification.class);
    }

    @Test
    @DisplayName("제보 삭제 성공 케이스")
    void deleteReport() {
        // given
        Report report = reportRepository.findById(firstReport.getId()).get();
        // when
        reportInfoService.deleteReport("kim",report.getId());
        // then
        assertThat(report.getActive().isValue()).isEqualTo(false);
    }

    @Test
    @DisplayName("제보 삭제 실패 케이스")
    void failDeleteReport() {
        // given
        Report report = reportRepository.findById(firstReport.getId()).get();
        // when then
        assertThatThrownBy(() -> reportInfoService.deleteReport("not kim",report.getId()))
                .isInstanceOf(MisMatchIdentification.class);
    }

    @DisplayName("제보 조회 성공 케이스")
    @ParameterizedTest
    @ValueSource(ints = {2,4,6})
    void searchAll(int length) {
        // given
        String title = "";
        String content = "";
        String reportType = "terror";
        double latitude = 36.36;
        double longitude = 126.36;
        String identification = "lee";
        for(int i = 0; i < length; i++) {
            title += "a";
            content += "a";
            reportRepository.save(Report.builder()
                    .title(Title.from(title))
                    .content(Content.from(content))
                    .reportType(ReportType.from(reportType))
                    .position(Position.of(longitude,latitude))
                    .identification(identification)
                    .active(Active.from(true)).build());
        }
        // when
        ReportResponseDto reportResponseDto = reportInfoService.searchAll(identification,longitude,latitude);
        // then
        assertThat(reportResponseDto.getReportList().size()).isEqualTo(length + 1);
    }

    @DisplayName("제보 조회 실패 케이스")
    @Test
    void uploadIPFiles() {
        List<MultipartFile> files = new ArrayList<>();
        MockMultipartFile file =
                new MockMultipartFile("file", "test.txt",
                        "text/plain", "test file".getBytes(StandardCharsets.UTF_8));
        files.add(file);

        // when then
        assertThatCode(() -> reportInfoService.uploadIPFiles("report",files,beforeReport))
                .doesNotThrowAnyException();
    }
}