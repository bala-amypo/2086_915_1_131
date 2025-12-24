package com.example.demo.repository;

import com.example.demo.entity.SupplyForecast;
import java.util.List;
import java.util.Optional;

public interface SupplyForecastRepository {
    SupplyForecast save(SupplyForecast forecast);
    Optional<SupplyForecast> findById(Long id);
    Optional<SupplyForecast> findFirstByOrderByGeneratedAtDesc();
    List<SupplyForecast> findAll();
}