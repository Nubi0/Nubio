package com.safeservice.domain.report.entity;

import com.safeservice.domain.common.BaseTimeEntity;
import com.safeservice.domain.report.entity.constant.reportfile.ReportFileType;
import com.safeservice.domain.report.entity.type.reportfile.Active;
import com.safeservice.domain.report.entity.type.reportfile.FileName;
import com.safeservice.domain.report.entity.type.reportfile.FileSize;
import com.safeservice.domain.report.entity.type.reportfile.FileUrl;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "report_file")
@SQLDelete(sql = "UPDATE report_file SET active = false WHERE id = ?")
@Where(clause = "active = true")
public class ReportFile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    @Enumerated(EnumType.STRING)
    private ReportFileType reportFileType;
    @Embedded
    private FileName fileName;

    @Embedded
    private FileUrl fileUrl;

    @Embedded
    private FileSize fileSize;

    @Embedded
    private Active active = Active.from(true);

    @Builder
    public ReportFile(Report report, ReportFileType reportFileType, FileName fileName, FileUrl fileUrl, FileSize fileSize,Active active) {
        this.report = report;
        this.reportFileType = reportFileType;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.active = active;
    }

    public void updateActive() {
        this.active = Active.from(false);
    }
}
