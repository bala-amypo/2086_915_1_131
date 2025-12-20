package com.example.demo.service.impl;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.service.SupplyForecastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyForecastServiceImpl implements SupplyForecastService {

    private final SupplyForecastRepository forecastRepository;

    public SupplyForecastServiceImpl(
            SupplyForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @Override
    public SupplyForecast createForecast(SupplyForecast forecast) {

        if (forecast.getAvailableSupplyMW() < 0) {
            throw new RuntimeException(">= 0");
        }

        if (forecast.getForecastStart()
                .isAfter(forecast.getForecastEnd())) {
            throw new RuntimeException("range");
        }

        return forecastRepository.save(forecast);
    }

    @Override
    public SupplyForecast updateForecast(Long id, SupplyForecast forecast) {
        SupplyForecast existing = getForecastById(id);
        existing.setAvailableSupplyMW(forecast.getAvailableSupplyMW());
        existing.setForecastStart(forecast.getForecastStart());
        existing.setForecastEnd(forecast.getForecastEnd());
        return forecastRepository.save(existing);
    }

    @Override
    public SupplyForecast getForecastById(Long id) {
        return forecastRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forecast not found"));
    }

    @Override
    public SupplyForecast getLatestForecast() {
        return forecastRepository.findFirstByOrderByGeneratedAtDesc()
                .orElseThrow(() -> new RuntimeException("No forecasts"));
    }

    @Override
    public List<SupplyForecast> getAllForecasts() {
        return forecastRepository.findAll();
    }
}
