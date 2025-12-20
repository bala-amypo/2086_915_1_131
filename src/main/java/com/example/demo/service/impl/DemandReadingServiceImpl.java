package com.example.demo.service.impl;

import com.example.demo.entity.DemandReading;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.DemandReadingService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class DemandReadingServiceImpl implements DemandReadingService {

    private final DemandReadingRepository readingRepository;
    private final ZoneRepository zoneRepository;

    public DemandReadingServiceImpl(
            DemandReadingRepository readingRepository,
            ZoneRepository zoneRepository) {
        this.readingRepository = readingRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public DemandReading createReading(DemandReading reading) {

        zoneRepository.findById(reading.getZone().getId())
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        if (reading.getDemandMW() < 0) {
            throw new RuntimeException(">= 0");
        }

        if (reading.getRecordedAt().isAfter(Instant.now())) {
            throw new RuntimeException("future");
        }

        return readingRepository.save(reading);
    }

    @Override
    public List<DemandReading> getReadingsForZone(Long zoneId) {
        zoneRepository.findById(zoneId)
                .orElseThrow(() -> new RuntimeException("Zone not found"));
        return readingRepository.findByZoneIdOrderByRecordedAtDesc(zoneId);
    }

    @Override
    public DemandReading getLatestReading(Long zoneId) {
        return readingRepository
                .findFirstByZoneIdOrderByRecordedAtDesc(zoneId)
                .orElseThrow(() -> new RuntimeException("No readings"));
    }

    @Override
    public List<DemandReading> getRecentReadings(Long zoneId, int limit) {
        List<DemandReading> all =
                readingRepository.findByZoneIdOrderByRecordedAtDesc(zoneId);
        return all.subList(0, Math.min(limit, all.size()));
    }
}
