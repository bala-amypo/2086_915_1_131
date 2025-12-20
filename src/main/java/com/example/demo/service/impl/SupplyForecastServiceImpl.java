package com.example.demo.service.impl;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.service.SupplyForecastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyForecastServiceImpl implements SupplyForecastService {

    private final SupplyForecastRepository forecastRepository;

    public SupplyForecastServiceImpl(SupplyForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @Override
    public SupplyForecast createForecast(SupplyForecast forecast) {
        return forecastRepository.save(forecast);
    }

    @Override
    public List<SupplyForecast> getForecastsForZone(Long zoneId) {
        return forecastRepository.findByZoneIdOrderByForecastTimeDesc(zoneId);
    }

    @Override
    public SupplyForecast getLatestForecast(Long zoneId) {
        return forecastRepository.findFirstByZoneIdOrderByForecastTimeDesc(zoneId)
                .orElse(null);
    }
}
