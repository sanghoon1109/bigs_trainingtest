package com.example.bigs_trainingtest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ForecastRequestDto {
    private String serviceKey;
    private String numOfRows;
    private String pageNo;
}
