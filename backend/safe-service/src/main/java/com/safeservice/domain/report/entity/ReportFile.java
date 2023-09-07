package com.safeservice.domain.report.entity;

import com.safeservice.domain.common.BaseTimeEntity;
import com.safeservice.domain.report.entity.type.report.Content;
import com.safeservice.domain.report.entity.type.report.Title;
import com.safeservice.domain.report.entity.type.reportfile.Active;
import com.safeservice.domain.report.entity.type.reportfile.FileName;
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
@SQLDelete(sql = "UPDATE report_file SET active = false WHERE id = ?")
@Where(clause = "active = false")
public class ReportFile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_file_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    @Embedded
    private FileName fileName;

    @Embedded
    private FileUrl fileUrl;

    @Embedded
    private Active active = Active.from(true);;

    @Builder
    public ReportFile(Report report, FileName fileName, FileUrl fileUrl, Active active) {
        this.report = report;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.active = active;
    }

    public void updateActive() {
        this.active = Active.from(false);
    }
}
