package com.example.demo.service.impl;

import com.example.demo.entity.DemandReading;
import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.service.LoadSheddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final DemandReadingRepository demandReadingRepository;

    @Autowired
    public LoadSheddingServiceImpl(DemandReadingRepository demandReadingRepository) {
        this.demandReadingRepository = demandReadingRepository;
    }

    @Override
    public List<LoadSheddingEvent> getAllEvents() {
        // Return dummy list for now or implement actual logic
        return List.of(); // empty list to satisfy interface
    }

    public DemandReading getLatestDemandReadingByZone(Long zoneId) {
        return demandReadingRepository
                .findFirstByZoneIdOrderByRecordedAtDesc(zoneId)
                .orElse(null);
    }
}
