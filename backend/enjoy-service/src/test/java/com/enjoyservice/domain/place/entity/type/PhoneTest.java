package com.enjoyservice.domain.place.entity.type;

import com.enjoyservice.domain.place.exception.InvalidPhoneNumberFormatException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneTest {

    @DisplayName("전화번호는 0 이상의 정수가 '-' 2개로 나누어져 있어야 한다. (2 ~ 3)자리 숫자-(3 ~ 4)자리 숫자-(4)자리 숫자")
    @ParameterizedTest
    @ValueSource(strings = {"0000-0000-0000", "1234-0000-0000", "0000-1234-0000", "1234-456-7894",
                            "0000-0000-1234", "111-1234-1234", "000-1234-1234", "000-000-0000",
                            "02-456-4567", "02-4578-4567","0000-0000-0000"})
    void validFormat(String input) {
        assertNotNull(Phone.from(input));
    }

//    @DisplayName("전화번호 포맷이 아니면 예외가 발생한다.")
//    @ParameterizedTest
//    @ValueSource(strings = {"7-0000-0000", "1234-7-0000", "0000-1234-7", "7-7-7894", "7-7897-7",
//            "723-72897-7", "7233-7897-71234", "answk-7897-7233", "7233-asd-7233", "123-123-7adsf"})
//    void invalidFormat(String input) {
//        assertThatThrownBy(() -> Phone.from(input))
//                .isInstanceOf(InvalidPhoneNumberFormatException.class);
//    }
}