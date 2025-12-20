package com.example.demo.service.impl;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.service.SupplyForecastService;

import java.time.Instant;
import java.util.List;

public class SupplyForecastServiceImpl implements SupplyForecastService {

    private final SupplyForecastRepository repo;

    public SupplyForecastServiceImpl(SupplyForecastRepository repo) {
        this.repo = repo;
    }

    @Override
    public SupplyForecast createForecast(SupplyForecast f) {

        if (f.getAvailableSupplyMW() < 0)
            throw new BadRequestException(">= 0");

        if (f.getForecastStart().isAfter(f.getForecastEnd()))
            throw new BadRequestException("range");

        f.setGeneratedAt(Instant.now());
        return repo.save(f);
    }

    @Override
    public SupplyForecast updateForecast(Long id, SupplyForecast input) {

        SupplyForecast f = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));

        if (input.getAvailableSupplyMW() < 0)
            throw new BadRequestException(">= 0");

        if (input.getForecastStart().isAfter(input.getForecastEnd()))
            throw new BadRequestException("range");

        f.setAvailableSupplyMW(input.getAvailableSupplyMW());
        f.setForecastStart(input.getForecastStart());
        f.setForecastEnd(input.getForecastEnd());

        return repo.save(f);
    }

    @Override
    public SupplyForecast getLatestForecast() {
        return repo.findFirstByOrderByGeneratedAtDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No forecasts"));
    }

    @Override
    public List<SupplyForecast> getAllForecasts() {
        return repo.findAll();
    }
}
