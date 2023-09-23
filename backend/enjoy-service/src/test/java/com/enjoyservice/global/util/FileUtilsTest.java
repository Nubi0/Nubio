package com.enjoyservice.global.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    @DisplayName("파일 저장 이름 확인")
    @Test
    void buildFileName() {
        // given
        String category = "test";
        String originalName = "originalFile.png";
        UUID uuid = UUID.randomUUID();
        // when
        String result = FileUtils.buildFileName(category, originalName, uuid);
        // then
        assertThat(result).isEqualTo("test/originalFile_" + uuid.toString() + ".png");
    }
}