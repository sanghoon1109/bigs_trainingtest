package com.example.bigs_trainingtest.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum StatusEnum {
    SUCCESS_INJECTION(HttpStatus.OK, "SUCCESS_INJECTION", "단기 예보를 DB에 저장하였습니다.");


    private final HttpStatus httpStatus;
    private final String description;
    private final String message;
}
