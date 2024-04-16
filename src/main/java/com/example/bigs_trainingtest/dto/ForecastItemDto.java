package com.example.bigs_trainingtest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForecastItemDto {
    private String baseDate;
    private String baseTime;
    private String fcstDate;
    private String fcstTime;
    private String category;
    private String fcstValue;
    private int nx;
    private int ny;
}
