package com.safeservice.domain.report.repository;

import com.safeservice.domain.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Long> {

    @Query("select r from Report r join fetch r.reportFiles")
    List<Report> searchAll();
}
