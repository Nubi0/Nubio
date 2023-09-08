package com.authenticationservice.domain.member.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {
    @Column(name = "password")
    private String value;

    private Password(String value) {
        this.value = value;
    }

    /**
     * String값의 value를 암호화한 뒤 생성해야됨
     *
     * (final Encryptor encryptor)와 같은 파라미터를 추가로 받아서 암호화해야함
     * return new Password(encryptor.encrypt(value));
     * @param value
     * @return
     */
    public static Password of(final String value, final PasswordEncoder passwordEncoder) {
        // TODO: 암호화 한 결과 넣어야 됨 / 검증도 추가
        return new Password(passwordEncoder.encode(value));
    }

    public String getValue() {
        return value;
    }
}
