package com.enjoyservice.domain.taste.repository;

import com.enjoyservice.domain.taste.entity.Taste;
import com.enjoyservice.domain.taste.entity.constant.DetailType;
import com.enjoyservice.domain.taste.entity.constant.Type;
import com.enjoyservice.domain.taste.entity.type.Active;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TasteRepositoryTest {

    @Autowired
    private TasteRepository tasteRepository;

    @DisplayName("상세 취향으로 취향을 조회한다.")
    @Test
    void findByDetailType() {
        // given
        String detailType = "노래방";
        Taste taste = Taste.builder()
                .type(Type.from("놀기"))
                .detailType(DetailType.from(detailType))
                .active(Active.from(true)).build();
        Taste savedTaste = tasteRepository.save(taste);
        // when
        Taste findByDetailType = tasteRepository.findByDetailType(DetailType.from(detailType));
        // then
        assertThat(findByDetailType.getId()).isEqualTo(savedTaste.getId());
        assertThat(findByDetailType.getDetailType().getDescription()).isEqualTo(detailType);
    }
}