package com.example.bigs_trainingtest.entity;

import com.example.bigs_trainingtest.dto.ForecastItemDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "forecast")
@NoArgsConstructor
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String baseDate; // 발표일자

    @Column(nullable = false)
    private String baseTime; // 발표시각

    @Column(nullable = false)
    private String fcstDate; // 예보일자

    @Column(nullable = false)
    private String fcstTime; // 예보시각

    @Column(nullable = false)
    private String fcstValue; // 예보 값

    @Column(nullable = false)
    private String category; // 자료구분문자

    @Column(nullable = false)
    private int nx; // 예보지점 X 좌표

    @Column(nullable = false)
    private int ny; // 예보지점 Y 좌표

    public Forecast(ForecastItemDto itemDto) {
        this.baseDate = itemDto.getBaseDate();
        this.baseTime = itemDto.getBaseTime();
        this.category = itemDto.getCategory();
        this.fcstTime = itemDto.getFcstTime();
        this.fcstDate = itemDto.getFcstDate();
        this.fcstValue = itemDto.getFcstValue();
        this.nx = itemDto.getNx();
        this.ny = itemDto.getNy();
    }

}
