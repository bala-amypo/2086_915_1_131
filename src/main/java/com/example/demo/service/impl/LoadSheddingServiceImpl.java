package com.example.demo.service.impl;

import com.example.demo.entity.DemandReading;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.service.LoadSheddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final DemandReadingRepository demandReadingRepository;

    @Autowired
    public LoadSheddingServiceImpl(DemandReadingRepository demandReadingRepository) {
        this.demandReadingRepository = demandReadingRepository;
    }

    @Override
    public DemandReading getLatestDemandReadingByZone(Long zoneId) {
        // Use Optional to handle no record case
        return demandReadingRepository
                .findFirstByZoneIdOrderByRecordedAtDesc(zoneId)
                .orElseThrow(() -> new RuntimeException("No demand reading found for zone " + zoneId));
    }
}
