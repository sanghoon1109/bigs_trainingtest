package com.example.bigs_trainingtest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastDataDto {
    private List<ForecastItemDto> items;

    public List<ForecastItemDto> getItems() {
        return items;
    }

    public void setItems(List<ForecastItemDto> items) {
        this.items = items;
    }
}