package com.example.demo.service.impl;

import com.example.demo.entity.DemandReading;
import com.example.demo.entity.Zone;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.DemandReadingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandReadingServiceImpl implements DemandReadingService {

    private final DemandReadingRepository repo;
    private final ZoneRepository zoneRepo;

    public DemandReadingServiceImpl(
            DemandReadingRepository repo,
            ZoneRepository zoneRepo
    ) {
        this.repo = repo;
        this.zoneRepo = zoneRepo;
    }

    @Override
    public DemandReading createReading(DemandReading reading) {
        return repo.save(reading);
    }

    @Override
    public List<DemandReading> getReadingsForZone(Long zoneId) {
        Zone zone = zoneRepo.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        return repo.findByZoneOrderByRecordedAtDesc(zone);
    }

    @Override
    public DemandReading getLatestReading(Long zoneId) {
        Zone zone = zoneRepo.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        return repo.findFirstByZoneOrderByRecordedAtDesc(zone)
                .orElseThrow(() -> new ResourceNotFoundException("No demand readings found"));
    }

    @Override
    public List<DemandReading> getRecentReadings(Long zoneId, int limit) {
        List<DemandReading> list = getReadingsForZone(zoneId);
        return list.size() <= limit ? list : list.subList(0, limit);
    }
}
