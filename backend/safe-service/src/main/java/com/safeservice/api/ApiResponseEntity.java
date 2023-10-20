package com.safeservice.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponseEntity<T> {

    private int code;
    private HttpStatus status;
    private T data;

    public ApiResponseEntity(HttpStatus status, T data) {
        this.code = status.value();
        this.status = status;
        this.data = data;
    }

    public static <T> ApiResponseEntity<T> of(HttpStatus httpStatus, T data) {
        return new ApiResponseEntity<>(httpStatus, data);
    }

    public static <T> ApiResponseEntity<T> ok(T data) {
        return of(HttpStatus.OK, data);
    }

    public static <T> ApiResponseEntity<T> create(T data) {
        return of(HttpStatus.CREATED, data);
    }
}
