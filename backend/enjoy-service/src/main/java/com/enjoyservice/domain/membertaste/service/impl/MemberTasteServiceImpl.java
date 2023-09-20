package com.enjoyservice.domain.membertaste.service.impl;

import com.enjoyservice.domain.membertaste.entity.MemberTaste;
import com.enjoyservice.domain.membertaste.repository.MemberTasteRepository;
import com.enjoyservice.domain.membertaste.service.MemberTasteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberTasteServiceImpl implements MemberTasteService {

    private final MemberTasteRepository memberTasteRepository;

    @Override
    public List<MemberTaste> findByMemberId(String memberId) {
        List<MemberTaste> allByMemberId = memberTasteRepository.findAllByMemberId(memberId);
        return allByMemberId;
    }

    @Override
    @Transactional
    public List<MemberTaste> saveAll(List<MemberTaste> memberTastes) {
        List<MemberTaste> savedMemberTastes = memberTasteRepository.saveAll(memberTastes);
        return savedMemberTastes;
    }

    @Override
    @Transactional
    public void deleteTaste(Long id) {
        memberTasteRepository.deleteById(id);
    }
}
