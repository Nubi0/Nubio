package com.safeservice.domain.report.entity;

import com.safeservice.domain.common.BaseTimeEntity;
import com.safeservice.domain.report.entity.constant.report.ReportType;
import com.safeservice.domain.report.entity.type.report.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "report")
@SQLDelete(sql = "UPDATE report SET active = false WHERE id = ?")
@Where(clause = "active = true")
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    @Column(name = "report_files_id")
    private List<ReportFile> reportFiles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @Embedded
    private Region region;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @Embedded
    private Position position;

    @Embedded
    private Active active = Active.from(true);

    @Column(name = "identification_id")
    private String identification;


    @Builder
    public Report(ReportType reportType, Title title, Content content, Active active,
                  Region region, Position position, String identification) {
        this.reportType = reportType;
        this.region = region;
        this.title = title;
        this.content = content;
        this.active = active;
        this.position = position;
        this.identification = identification;
    }

    public void updateActive() {
        this.active = Active.from(false);
    }

    public void addReportFile(ReportFile reportFile) {
        reportFiles.add(reportFile);
    }

    public void update(Report report) {
        this.reportType = report.getReportType();
        this.title = Title.from(report.getTitle().getValue());
        this.content = Content.from(report.getContent().getValue());
        this.position = Position.of(report.getPosition().getLongitude(),report.getPosition().getLatitude());
    }
}
