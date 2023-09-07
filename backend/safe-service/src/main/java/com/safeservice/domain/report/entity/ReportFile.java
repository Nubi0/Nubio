package com.safeservice.domain.report.entity;

import com.safeservice.domain.report.entity.type.reportfile.Active;
import com.safeservice.domain.report.entity.type.reportfile.FileName;
import com.safeservice.domain.report.entity.type.reportfile.FileUrl;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    @Embedded
    private FileName fileName;

    @Embedded
    private FileUrl fileUrl;

    @Embedded
    private Active active;

    @Builder
    public ReportFile(Report report, FileName fileName, FileUrl fileUrl, Active active) {
        this.report = report;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.active = active;
    }
}
