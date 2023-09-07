package com.safeservice.domain.report.entity;

import com.safeservice.domain.common.BaseTimeEntity;
import com.safeservice.domain.report.entity.constant.report.ReportType;
import com.safeservice.domain.report.entity.type.report.Active;
import com.safeservice.domain.report.entity.type.report.Content;
import com.safeservice.domain.report.entity.type.report.Title;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @Embedded
    private Active active;

    @Builder
    public Report(ReportType reportType, Title title, Content content, Active active) {
        this.reportType = reportType;
        this.title = title;
        this.content = content;
        this.active = active;
    }

    public void updateActive() {
        this.active = Active.from(false);
    }

    public void update(Report report) {
        this.reportType = report.getReportType();
        this.title = Title.from(report.getTitle().getValue());
        this.content = Content.from(report.getContent().getValue());
    }
}
