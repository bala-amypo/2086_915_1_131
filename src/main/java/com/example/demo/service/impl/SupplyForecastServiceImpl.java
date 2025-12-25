package com.example.demo.service.impl;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.service.SupplyForecastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyForecastServiceImpl implements SupplyForecastService {

    private final SupplyForecastRepository repository;

    public SupplyForecastServiceImpl(SupplyForecastRepository repository) {
        this.repository = repository;
    }

    @Override
    public SupplyForecast save(SupplyForecast supplyForecast) {
        return repository.save(supplyForecast);
    }

    @Override
    public List<SupplyForecast> getAll() {
        return repository.findAll();
    }
}
