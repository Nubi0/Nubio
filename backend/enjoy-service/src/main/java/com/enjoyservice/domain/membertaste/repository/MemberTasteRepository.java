package com.enjoyservice.domain.membertaste.repository;

import com.enjoyservice.domain.membertaste.entity.MemberTaste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberTasteRepository extends JpaRepository<MemberTaste, Long> {
    List<MemberTaste> findAllByMemberId(String id);
}
