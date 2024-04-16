package com.example.bigs_trainingtest.dto;

import com.example.bigs_trainingtest.entity.Forecast;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ForecastResponseDto {

    private Long id; // db에 적재된 id
    private String baseDate; // 발표일자
    private String baseTime; // 발표시각
    private String fcstDate; // 예보일자
    private String fcstTime; // 예보시각
    private String fcstValue; // 예보 값
    private String category; // 자료구분문자
    private int nx; // 예보지점 X 좌표
    private int ny; // 예보지점 Y 좌표

    public ForecastResponseDto(Forecast forecast) {
        this.id = forecast.getId();
        this.baseDate = forecast.getBaseDate();
        this.baseTime = forecast.getBaseTime();
        this.fcstTime = forecast.getFcstTime();
        this.fcstDate = forecast.getFcstDate();
        this.fcstValue = forecast.getFcstValue();
        this.category = forecast.getCategory();
        this.nx = forecast.getNx();
        this.ny = forecast.getNy();
    }

}
