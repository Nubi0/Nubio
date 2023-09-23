package com.safeservice.domain.report.service.Impl;
import com.safeservice.domain.report.entity.Report;
import com.safeservice.domain.report.entity.constant.report.ReportType;
import com.safeservice.domain.report.entity.type.report.Active;
import com.safeservice.domain.report.entity.type.report.Content;
import com.safeservice.domain.report.entity.type.report.Position;
import com.safeservice.domain.report.entity.type.report.Title;
import com.safeservice.domain.report.exception.InvalidTitleLengthException;
import com.safeservice.domain.report.exception.MisMatchIdentification;
import com.safeservice.domain.report.repository.ReportFileRepository;
import com.safeservice.domain.report.repository.ReportRepository;
import com.safeservice.domain.report.service.ReportFileService;
import com.safeservice.domain.report.service.ReportService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReportServiceImplTest {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportFileRepository reportFileRepository;

    @Autowired
    private ReportFileService reportFileService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private EntityManager em;

    private Report firstReport;
    private Report beforeReport;

    @BeforeEach
    void before() {
        firstReport = Report.builder()
                .reportType(ReportType.TERROR)
                .title(Title.from("first"))
                .content(Content.from("first content"))
                .position(Position.of(123.12, 26.26))
                .identification("kim")
                .active(Active.from(true)).build();

        beforeReport = reportRepository.save(firstReport);

        reportFileService.saveAccuseFile("first", "first url", 123L, beforeReport);
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
                .position(Position.of(123.12, 26.26))
                .identification("kim")
                .active(Active.from(true)).build();

        // when
        Report savedReport = reportService.save(report);

        // then
        assertThat(report).isEqualTo(savedReport);
        assertThat(report.getReportType()).isEqualTo(savedReport.getReportType());
        assertThat(report.getTitle()).isEqualTo(savedReport.getTitle());
        assertThat(report.getContent()).isEqualTo(savedReport.getContent());
        assertThat(report.getPosition()).isEqualTo(savedReport.getPosition());
        assertThat(report.getIdentification()).isEqualTo(savedReport.getIdentification());
    }

    @DisplayName("제보 수정 성공 케이스")
    @Test
    void update() {
        // given
        Report report = Report.builder()
                .reportType(ReportType.ACCIDENT)
                .title(Title.from("change"))
                .content(Content.from("success change"))
                .position(Position.of(125.12, 28.26))
                .active(Active.from(true)).build();

        // when
        reportService.update(report,beforeReport.getId(), "kim");

        // then
        assertThat(beforeReport.getReportType()).isEqualTo(report.getReportType());
        assertThat(beforeReport.getTitle().getValue()).isEqualTo(report.getTitle().getValue());
        assertThat(beforeReport.getContent().getValue()).isEqualTo(report.getContent().getValue());
    }

    @DisplayName("제보 수정 실패 케이스")
    @Test
    void failUpdate() {
        // given
        Report report = Report.builder()
                .reportType(ReportType.ACCIDENT)
                .title(Title.from("fail"))
                .content(Content.from("fail change"))
                .position(Position.of(125.12, 28.26))
                .active(Active.from(true)).build();

        // when then
        assertThatThrownBy(() -> reportService.update(report,beforeReport.getId(), "not kim"))
                .isInstanceOf(MisMatchIdentification.class);
    }

    @DisplayName("제보 논리적 삭제 케이스")
    @Test
    void delete() {
        // given
        Report report = Report.builder()
                .reportType(ReportType.ACCIDENT)
                .title(Title.from("delete"))
                .content(Content.from("delete test"))
                .position(Position.of(123.12, 26.26))
                .identification("kim")
                .active(Active.from(true)).build();
        Report savedReport = reportRepository.save(report);

        // when
        reportService.delete(savedReport.getId());
        // then
        assertThat(savedReport.getActive().isValue()).isEqualTo(false);
    }

    @DisplayName("제보 제목 길이 제한 실패 케이스")
    @ParameterizedTest
    @ValueSource(ints = {201, 202, 300, 400})
    void reportLengthFail(int titleLength) {
        // given
        String t = "";
        for(int i = 0; i < titleLength; i++) {
            t += "a";
        }

        String str = t;
        // when then
        assertThatThrownBy(() -> Title.from(str))
                .isInstanceOf(InvalidTitleLengthException.class);
    }

    @DisplayName("제보 제목 길이 제한 성공 케이스")
    @ParameterizedTest
    @ValueSource(ints = {10, 50, 100, 199})
    void reportLengthSuccess(int length) {
        // given
        String s = "";
        for(int i = 0; i < length; i++) {
            s += "a";
        }

        String st = s;
        // when then
        assertThatCode(() -> Title.from(st)).doesNotThrowAnyException();
    }

    @DisplayName("@SQLDelete, @Where 적용 확인")
    @Test
    void softDelete() {
        // given
        // when
        reportRepository.delete(beforeReport);
        em.flush();
        em.clear();

        Optional<Report> report = reportRepository.findById(beforeReport.getId());

        // then
        assertThat(report.isEmpty()).isEqualTo(true);
    }

    @DisplayName("전체 제보 조회 성공 케이스")
    @ParameterizedTest
    @ValueSource(ints = {2, 5, 10})
//    @Rollback(value = false)
    void searchReport(int length) {
        // given
        for (int i = 0; i < length; i ++) {
            Report report = Report.builder()
                    .reportType(ReportType.ACCIDENT)
                    .title(Title.from("search"))
                    .content(Content.from("search test"))
                    .position(Position.of(123.12, 26.26))
                    .identification("kim")
                    .active(Active.from(true)).build();
            Report savedReport = reportService.save(report);

            reportFileService.saveAccuseFile("file name","file url", 123L, savedReport);
        }

        // when
        List<Report> reports = reportService.searchReport(123.12,26.26);

        // then
        assertThat(reports.size()).isEqualTo(length + 1);
        for(int i = 0; i < length + 1; i ++ ) {
            assertThat(reports.get(i).getReportFiles().get(0)).
                    isEqualTo(reportFileRepository.findById(reports.get(i).getReportFiles().get(0).getId()).get());
        }
    }
}