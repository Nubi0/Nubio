package com.enjoyservice.domain.membertaste.repository;

import com.enjoyservice.domain.membertaste.entity.MemberTaste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberTasteRepository extends JpaRepository<MemberTaste, Long> {
    @Query("select m, t from MemberTaste m left join fetch Taste t on m.taste = t")
    List<MemberTaste> findAllByMemberId(String id);
    void deleteAllByMemberId(String id);
}
