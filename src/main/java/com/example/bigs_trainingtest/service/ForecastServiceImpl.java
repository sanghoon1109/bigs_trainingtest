package com.example.bigs_trainingtest.service;

import com.example.bigs_trainingtest.common.CustomResponseEntity;
import com.example.bigs_trainingtest.common.StatusEnum;
import com.example.bigs_trainingtest.dto.ForecastDataDto;
import com.example.bigs_trainingtest.dto.ForecastItemDto;
import com.example.bigs_trainingtest.dto.ForecastRequestDto;
import com.example.bigs_trainingtest.dto.ForecastResponseDto;
import com.example.bigs_trainingtest.entity.Forecast;
import com.example.bigs_trainingtest.repository.ForecastRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForecastServiceImpl implements ForecastService {

    private final ForecastRepository forecastRepository;
    private final RestTemplate restTemplate;

    @Override
    public void injectionForecast(ForecastRequestDto requestDto, LocalDateTime localDateTime) {
        try {
            // URI 작성
            URI uri = makeRequestURIForWeatherData(requestDto, localDateTime);

            // GET 요청을 보내기 위해 RestTemplate의 exchange 메서드를 사용
            ResponseEntity<String> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    String.class
            );

            jsonParsing(response.getBody());
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
        }
    }

    @Override
    public ResponseEntity<List<ForecastResponseDto>> getForecast() {
        List<ForecastResponseDto> forecastList = forecastRepository.findAll().stream().map(ForecastResponseDto::new).toList();
        if (forecastList.isEmpty()) {
            // HTTP 상태 코드 204를 반환
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(forecastList);
    }


    private URI makeRequestURIForWeatherData(ForecastRequestDto requestDto, LocalDateTime localDateTime) {
        String serviceKey = requestDto.getServiceKey();
        String baseDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseTime = localDateTime.format(DateTimeFormatter.ofPattern("HHmm"));
        int nx = 61; // 오픈 API 활용 가이드에 문충로가 따로 기입되어 있지 않아 일단 의정부시 위경도로 작성
        int ny = 130;

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("apis.data.go.kr")
                .path("1360000/VilageFcstInfoService_2.0/getVilageFcst")
                .queryParam("serviceKey", serviceKey)
                .queryParam("numOfRows", requestDto.getNumOfRows())
                .queryParam("pageNo", requestDto.getPageNo())
                .queryParam("dataType", "JSON")
                .queryParam("base_date", baseDate)
                .queryParam("base_time", baseTime)
                .queryParam("nx", nx)
                .queryParam("ny", ny)
                .build(true);



        return uriComponents.toUri();
    }

    private void jsonParsing(String responseBody) {
        // JSON 데이터 파싱 작업
        JSONObject jObject = new JSONObject(responseBody);

        JSONObject responseObject = jObject.getJSONObject("response");
        JSONObject bodyObject = responseObject.getJSONObject("body");
        JSONObject itemsObject = bodyObject.getJSONObject("items");
        JSONArray itemArray = itemsObject.getJSONArray("item");

        for (int i = 0; i < itemArray.length(); i++) {
            JSONObject obj = itemArray.getJSONObject(i);
            ForecastItemDto itemDto = new ForecastItemDto();
            itemDto.setCategory(obj.getString("category"));
            itemDto.setBaseDate(obj.getString("baseDate"));
            itemDto.setBaseTime(obj.getString("baseTime"));
            itemDto.setFcstDate(obj.getString("fcstDate"));
            itemDto.setFcstTime(obj.getString("fcstTime"));
            itemDto.setFcstValue(obj.getString("fcstValue"));
            itemDto.setNx(obj.getInt("nx"));
            itemDto.setNy(obj.getInt("ny"));
            forecastRepository.save(new Forecast(itemDto));
        }
    }

}

