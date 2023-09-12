package com.safeservice.domain.report.service.Impl;

import com.safeservice.config.AwsS3MockConfig;
import com.safeservice.domain.report.entity.Report;
import com.safeservice.domain.report.entity.ReportFile;
import com.safeservice.domain.report.entity.constant.report.ReportType;
import com.safeservice.domain.report.entity.type.report.Active;
import com.safeservice.domain.report.entity.type.report.Content;
import com.safeservice.domain.report.entity.type.report.Position;
import com.safeservice.domain.report.entity.type.report.Title;
import com.safeservice.domain.report.entity.type.reportfile.FileName;
import com.safeservice.domain.report.entity.type.reportfile.FileSize;
import com.safeservice.domain.report.entity.type.reportfile.FileUrl;
import com.safeservice.domain.report.exception.InvalidFileSizeException;
import com.safeservice.domain.report.exception.InvalidTitleLengthException;
import com.safeservice.domain.report.repository.ReportFileRepository;
import com.safeservice.domain.report.repository.ReportRepository;
import com.safeservice.domain.report.service.ReportFileService;
import io.findify.s3mock.S3Mock;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReportFileServiceImplTest {

    @Autowired
    private ReportFileService reportFileService;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportFileRepository reportFileRepository;

    @Autowired
    private EntityManager em;

    private Report beforeReport;

    private ReportFile beforeReportFile;

    @BeforeEach
    void before() {
        Report firstReport = Report.builder()
                .reportType(ReportType.TERROR)
                .title(Title.from("first"))
                .content(Content.from("first content"))
                .position(Position.of(123.13,36.36))
                .identification("kim")
                .active(Active.from(true)).build();
        beforeReport = reportRepository.save(firstReport);

        ReportFile firstReportFile = ReportFile.builder()
                .fileName(FileName.from("before"))
                .fileUrl(FileUrl.from("before url"))
                .fileSize(FileSize.from(123L))
                .report(beforeReport)
                .active(com.safeservice.domain.report.entity.type.reportfile.Active.from(true)).build();
        beforeReportFile = reportFileRepository.save(firstReportFile);
    }

    @AfterEach
    void after() {
        reportRepository.deleteAll();
//        s3Mock.stop();
    }

    @DisplayName("파일 저장 성공 케이스")
    @Test
    void saveAccuseFile() {
        // given
        String fileName = "first";
        String fileUrl = "first url";
        Long fileSize = 123L;

        // when
        ReportFile reportFile = reportFileService.saveAccuseFile(fileName, fileUrl, fileSize, beforeReport);

        // then
        assertThat(reportFile.getFileName().getValue()).isEqualTo(fileName);
        assertThat(reportFile.getFileUrl().getValue()).isEqualTo(fileUrl);
        assertThat(reportFile.getFileSize().getValue()).isEqualTo(fileSize);
    }

    @DisplayName("파일 논리적 삭제 케이스")
    @Test
    void delete() {

        // given
        String fileName = "delete";
        String fileUrl = "delete url";
        Long fileSize = 123L;
        ReportFile reportFile = reportFileService.saveAccuseFile(fileName, fileUrl, fileSize, beforeReport);

        // when
        reportFileService.delete(reportFile.getId());

        // then
        assertThat(reportFile.getActive().isValue()).isEqualTo(false);
    }

    @DisplayName("파일 용량 제한 초과 케이스")
    @ParameterizedTest
    @ValueSource(longs = {1000001L, 2000000L, 3000000L, 4000000L})
    void reportLengthFail(Long size) {
        // given
        String fileName = "test fail";
        String fileUrl = "test fail url";
        Long failFileSize = size;

        // when then
        assertThatThrownBy(() -> reportFileService.saveAccuseFile(fileName, fileUrl, failFileSize, beforeReport))
                .isInstanceOf(InvalidFileSizeException.class);
    }

    @DisplayName("파일 용량 제한 성공 케이스")
    @ParameterizedTest
    @ValueSource(longs = {10L, 1000L, 10000L, 1000000L})
    void reportLengthSuccess(Long size) {
        // given
        String fileName = "test success";
        String fileUrl = "test success url";
        Long successFileSize = size;

        // when then
        assertThatCode(() -> reportFileService.saveAccuseFile(fileName, fileUrl, successFileSize, beforeReport))
                .doesNotThrowAnyException();
    }

    @DisplayName("@SQLDelete, @Where 적용 확인")
    @Test
    void softDelete() {
        // given
        // when
        reportFileRepository.delete(beforeReportFile);
        em.flush();
        em.clear();

        Optional<ReportFile> reportFile = reportFileRepository.findById(beforeReportFile.getId());

        // then
        assertThat(reportFile.isEmpty()).isEqualTo(true);
    }
}