package com.safeservice.domain.report.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.safeservice.domain.report.entity.QReport;
import com.safeservice.domain.report.entity.QReportFile;
import com.safeservice.domain.report.entity.Report;
import com.safeservice.domain.report.entity.constant.reportfile.ReportFileType;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.safeservice.domain.report.entity.QReport.report;
import static com.safeservice.domain.report.entity.QReportFile.reportFile;

@RequiredArgsConstructor
public class ReportRepositoryImpl implements ReportRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Report searchAllReport(double longitude, double latitude, String region) {
        return jpaQueryFactory.selectFrom(report)
                .leftJoin(report.reportFiles, reportFile).fetchJoin()
                .where(report.region.value.eq(region))
                .orderBy(report.createTime.desc())
                .limit(1)
                .fetchOne();
    }

    @Override
    public List<Report> searchAll() {
        return jpaQueryFactory.selectFrom(report)
                .leftJoin(report.reportFiles, reportFile).fetchJoin()
                .fetch();
    }

}
