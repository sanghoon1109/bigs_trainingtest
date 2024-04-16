package com.example.bigs_trainingtest.service;

import com.example.bigs_trainingtest.dto.ForecastRequestDto;
import com.example.bigs_trainingtest.dto.ForecastResponseDto;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

public interface ForecastService {
    /*
    * 단기예보를 DB에 저장한다
    *
    * */
    void injectionForecast(ForecastRequestDto requestDto, LocalDateTime localDateTime);

    ResponseEntity<List<ForecastResponseDto>> getForecast();

    /*
     * DB 에 저장된 데이터를 조회한다.
     *
     * */
    
}
