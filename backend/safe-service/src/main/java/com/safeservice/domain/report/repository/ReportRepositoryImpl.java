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
    public List<Report> searchAllReport() {
        return jpaQueryFactory.selectFrom(report)
                .leftJoin(report.reportFiles, reportFile).fetchJoin()
                .fetch();
    }
}
