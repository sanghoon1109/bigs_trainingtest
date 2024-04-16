package com.example.bigs_trainingtest.controller;

import com.example.bigs_trainingtest.common.CustomResponseEntity;
import com.example.bigs_trainingtest.common.StatusEnum;
import com.example.bigs_trainingtest.dto.ForecastRequestDto;
import com.example.bigs_trainingtest.dto.ForecastResponseDto;
import com.example.bigs_trainingtest.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;

    @PostMapping("/forecast") // 단기 예보를 DB에 저장하게 하는 API
    public ResponseEntity<CustomResponseEntity> injectionForecast(
            @RequestBody ForecastRequestDto forecastRequestDto) {
        LocalDateTime localDateTime = LocalDateTime.now();

        forecastService.injectionForecast(forecastRequestDto, localDateTime);
        return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_INJECTION);
    }

    @GetMapping("/forecast") // 단기 예보를 조회 하는 API
    public ResponseEntity<List<ForecastResponseDto>> getForecast() {
        return forecastService.getForecast();
    }


}
