package com.enjoyservice.domain.taste.service.impl;

import com.enjoyservice.domain.taste.entity.Taste;
import com.enjoyservice.domain.taste.entity.constant.DetailType;
import com.enjoyservice.domain.taste.entity.constant.Type;
import com.enjoyservice.domain.taste.entity.type.Active;
import com.enjoyservice.domain.taste.service.TasteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TasteServiceImplTest {

    @Autowired
    private TasteService tasteService;

    @DisplayName("취향 저장에 성공한다.")
    @Test
    void saveTaste() {
        // given
        Taste taste = Taste.builder()
                .type(Type.from("놀기"))
                .detailType(DetailType.from("노래방"))
                .active(Active.from(true)).build();
        // when
        Taste savedTaste = tasteService.saveTaste(taste);
        // then
        assertThat(savedTaste.getType().getDescription()).isEqualTo(taste.getType().getDescription());
        assertThat(savedTaste.getDetailType().getDescription()).isEqualTo(taste.getDetailType().getDescription());
    }

    @DisplayName("취향 조회에 성공한다.")
    @Test
    void searchByDetailType() {
        // given
        String detailType = "노래방";
        Taste taste = Taste.builder()
                .type(Type.from("놀기"))
                .detailType(DetailType.from(detailType))
                .active(Active.from(true)).build();
        Taste savedTaste = tasteService.saveTaste(taste);
        // when
        Taste findByDetail = tasteService.searchByDetailType(DetailType.from(detailType));
        // then
        assertThat(savedTaste.getId()).isEqualTo(findByDetail.getId());
        assertThat(savedTaste.getType().getDescription()).isEqualTo(findByDetail.getType().getDescription());
        assertThat(savedTaste.getDetailType().getDescription()).isEqualTo(findByDetail.getDetailType().getDescription());
    }
}