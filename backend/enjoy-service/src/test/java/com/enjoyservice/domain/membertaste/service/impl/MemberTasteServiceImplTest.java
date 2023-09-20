package com.enjoyservice.domain.membertaste.service.impl;

import com.enjoyservice.domain.membertaste.entity.MemberTaste;
import com.enjoyservice.domain.membertaste.repository.MemberTasteRepository;
import com.enjoyservice.domain.membertaste.service.MemberTasteService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTasteServiceImplTest {

    @Autowired
    private MemberTasteService memberTasteService;

    @Autowired
    private MemberTasteRepository memberTasteRepository;

    static final String beforeMemberId = "beforeMemberId";

    @DisplayName("회원 id로 등록된 모든 회원 취향을 조회한다.")
    @Test
    void findByMemberId() {
        // givne
        int length = 5;
        for (int i = 0; i < length; i++) {
            MemberTaste beforeMemberTaste = MemberTaste.builder()
                    .memberId(beforeMemberId).build();
            memberTasteRepository.save(beforeMemberTaste);
        }
        // when
        List<MemberTaste> findByMemberId = memberTasteService.findByMemberId(beforeMemberId);
        // then
        assertThat(findByMemberId.size()).isEqualTo(length);
        for (int i = 0; i < findByMemberId.size(); i++) {
            assertThat(findByMemberId.get(i).getMemberId()).isEqualTo(beforeMemberId);
        }
    }

    @DisplayName("리스트로 주어진 모든 회원 취향을 저장한다.")
    @Test
    void saveAll() {
        // given
        int length = 5;
        List<MemberTaste> memberTastes = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            MemberTaste memberTaste = MemberTaste.builder()
                    .memberId(beforeMemberId).build();
            memberTastes.add(memberTaste);
        }
        // when
        List<MemberTaste> savedMemberTastes = memberTasteService.saveAll(memberTastes);
        // then
        assertThat(savedMemberTastes.size()).isEqualTo(length);
        for (int i = 0; i < savedMemberTastes.size(); i++) {
            assertThat(savedMemberTastes.get(i).getMemberId()).isEqualTo(beforeMemberId);
        }
    }

    @DisplayName("취향 id를 통하여 취향을 삭제한다.")
    @Test
    void deleteTaste() {
        // given
        int length = 5;
        List<MemberTaste> memberTastes = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            MemberTaste memberTaste = MemberTaste.builder()
                    .memberId(beforeMemberId).build();
            memberTastes.add(memberTaste);
        }
        // when
        memberTasteService.deleteTaste(beforeMemberId);
        List<MemberTaste> findByIdMT = memberTasteRepository.findAllByMemberId(beforeMemberId);
        // then
        assertThat(findByIdMT.isEmpty()).isEqualTo(true);

    }
}