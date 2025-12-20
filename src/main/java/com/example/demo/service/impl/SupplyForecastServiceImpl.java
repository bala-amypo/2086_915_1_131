package com.example.demo.service.impl;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.service.SupplyForecastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Make sure this is present
public class SupplyForecastServiceImpl implements SupplyForecastService {

    private final SupplyForecastRepository repository;

    public SupplyForecastServiceImpl(SupplyForecastRepository repository) {
        this.repository = repository;
    }

    @Override
    public SupplyForecast createForecast(SupplyForecast forecast) {
        return repository.save(forecast);
    }

    @Override
    public List<SupplyForecast> getForecastsForZone(Long zoneId) {
        return repository.findByZoneIdOrderByForecastTimeDesc(zoneId);
    }

    @Override
    public SupplyForecast getLatestForecast(Long zoneId) {
        return repository.findFirstByZoneIdOrderByForecastTimeDesc(zoneId);
    }
}
