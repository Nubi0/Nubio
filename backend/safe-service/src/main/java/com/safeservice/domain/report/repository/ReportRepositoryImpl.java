package com.safeservice.domain.report.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.safeservice.domain.report.entity.QReport;
import com.safeservice.domain.report.entity.QReportFile;
import com.safeservice.domain.report.entity.Report;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.safeservice.domain.report.entity.QReport.report;
import static com.safeservice.domain.report.entity.QReportFile.reportFile;

@RequiredArgsConstructor
public class ReportRepositoryImpl implements ReportRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Report> searchAllReport(double longitude, double latitude) {
        return jpaQueryFactory.selectFrom(report)
                .leftJoin(report.reportFiles, reportFile).fetchJoin()
                .where(
                        report.position.latitude.between(subtractValue(latitude), plusValue(latitude)),
                        report.position.longitude.between(subtractValue(longitude),plusValue(longitude))
                )
                .fetch();
    }

    private double subtractValue(double value) {
        return value - 1;
    }

    private double plusValue(double value) {
        return value + 1;
    }
}
