package com.example.demo.service.impl;

import com.example.demo.entity.DemandReading;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.service.DemandReadingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandReadingServiceImpl implements DemandReadingService {

    private final DemandReadingRepository repository;

    public DemandReadingServiceImpl(DemandReadingRepository repository) {
        this.repository = repository;
    }

    @Override
    public DemandReading save(DemandReading reading) {
        return repository.save(reading);
    }

    @Override
    public List<DemandReading> getAll() {
        return repository.findAll();
    }

    @Override
    public DemandReading getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<DemandReading> getRecentReadings(Long zoneId, int limit) {
        return repository.findTopByZoneIdOrderByRecordedAtDesc(zoneId, limit);
    }
}
