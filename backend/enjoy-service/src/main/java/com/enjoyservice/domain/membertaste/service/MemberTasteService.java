package com.enjoyservice.domain.membertaste.service;

import com.enjoyservice.domain.membertaste.entity.MemberTaste;

import java.util.List;

public interface MemberTasteService {
    List<MemberTaste> findByMemberId(String memberId);
    List<MemberTaste> saveAll(List<MemberTaste> memberTastes);
    void deleteTaste(Long id);
}
