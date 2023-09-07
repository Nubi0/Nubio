package com.safeservice.domain.safebell.repository;

import com.safeservice.domain.safebell.entity.SafeBell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SafeBellRepository extends JpaRepository<SafeBell, Long> {

}
