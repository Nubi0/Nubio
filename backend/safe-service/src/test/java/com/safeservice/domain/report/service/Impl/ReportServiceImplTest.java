package com.safeservice.domain.report.service.Impl;

import com.safeservice.domain.report.entity.Report;
import com.safeservice.domain.report.entity.constant.report.ReportType;
import com.safeservice.domain.report.entity.type.report.Active;
import com.safeservice.domain.report.entity.type.report.Content;
import com.safeservice.domain.report.entity.type.report.Title;
import com.safeservice.domain.report.repository.ReportRepository;
import com.safeservice.domain.report.service.ReportService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReportServiceImplTest {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportService reportService;

    private Report beforeReport;

    @BeforeEach
    void before() {
        Report firstReport = Report.builder()
                .reportType(ReportType.TERROR)
                .title(Title.from("first"))
                .content(Content.from("first content"))
                .active(Active.from(true)).build();
        beforeReport = reportRepository.save(firstReport);
    }

    @AfterEach
    void after() {
        reportRepository.deleteAll();
    }

    @DisplayName("제보 저장 케이스")
    @Test
    void save() {
        // given
        Report report = Report.builder()
                .reportType(ReportType.TERROR)
                .title(Title.from("success"))
                .content(Content.from("success case"))
                .active(Active.from(true)).build();

        // when
        Report savedReport = reportService.save(report);

        // then
        assertThat(report).isEqualTo(savedReport);
        assertThat(report.getReportType()).isEqualTo(savedReport.getReportType());
        assertThat(report.getTitle()).isEqualTo(savedReport.getTitle());
        assertThat(report.getContent()).isEqualTo(savedReport.getContent());
    }

    @DisplayName("제보 수정 케이스")
    @Test
    void update() {
        // given
        Report report = Report.builder()
                .reportType(ReportType.ACCIDENT)
                .title(Title.from("change"))
                .content(Content.from("success change"))
                .active(Active.from(true)).build();

        // when
        reportService.update(report,beforeReport.getId());

        // then
        assertThat(beforeReport.getReportType()).isEqualTo(report.getReportType());
        assertThat(beforeReport.getTitle().getValue()).isEqualTo(report.getTitle().getValue());
        assertThat(beforeReport.getContent().getValue()).isEqualTo(report.getContent().getValue());
    }

    @DisplayName("제보 논리적 삭제 케이스")
    @Test
    void delete() {
        // given
        Report report = Report.builder()
                .reportType(ReportType.ACCIDENT)
                .title(Title.from("delete"))
                .content(Content.from("delete test"))
                .active(Active.from(true)).build();
        Report savedReport = reportRepository.save(report);

        // when
        reportService.delete(savedReport.getId());

        // then
        assertThat(savedReport.getActive().isValue()).isEqualTo(false);
    }
}