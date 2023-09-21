package com.enjoyservice.domain.membertaste.repository;

import com.enjoyservice.domain.membertaste.entity.MemberTaste;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTasteRepositoryTest {


    @Autowired
    private MemberTasteRepository memberTasteRepository;

    static final String beforeMemberId = "beforeMemberId";

    @DisplayName("회원 id로 등록된 모든 취향을 조회한다.")
    @Test
    void findAllByMemberId() {
        // given
        int length = 5;
        for (int i = 0; i < length; i++) {
            MemberTaste beforeMemberTaste = MemberTaste.builder()
                    .memberId(beforeMemberId).build();
            memberTasteRepository.save(beforeMemberTaste);
        }
        // when
        List<MemberTaste> allByMemberId = memberTasteRepository.findAllByMemberId(beforeMemberId);
        // then
        for (int i = 0; i < allByMemberId.size(); i++) {
            assertThat(allByMemberId.get(i).getMemberId()).isEqualTo(beforeMemberId);
        }
    }
}