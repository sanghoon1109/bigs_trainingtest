package com.example.bigs_trainingtest.repository;

import com.example.bigs_trainingtest.entity.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {
}
