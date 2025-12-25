package com.example.demo.service.impl;

import com.example.demo.entity.DemandReading;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.service.LoadSheddingService;
import org.springframework.stereotype.Service;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final DemandReadingRepository readingRepo;

    public LoadSheddingServiceImpl(DemandReadingRepository readingRepo) {
        this.readingRepo = readingRepo;
    }

    @Override
    public DemandReading getLatestReading(Long zoneId) {
        return readingRepo
                .findFirstByZoneIdOrderByRecordedAtDesc(zoneId)
                .orElse(null);
    }
}
