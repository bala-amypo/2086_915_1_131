package com.example.demo.service.impl;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.SupplyForecast;
import com.example.demo.entity.Zone;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.LoadSheddingService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final LoadSheddingEventRepository repo;
    private final SupplyForecastRepository forecastRepo;
    private final ZoneRepository zoneRepo;

    public LoadSheddingServiceImpl(
            LoadSheddingEventRepository repo,
            SupplyForecastRepository forecastRepo,
            ZoneRepository zoneRepo
    ) {
        this.repo = repo;
        this.forecastRepo = forecastRepo;
        this.zoneRepo = zoneRepo;
    }

    @Override
    public LoadSheddingEvent triggerLoadShedding(Long forecastId) {

        SupplyForecast forecast = forecastRepo.findById(forecastId)
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));

        Zone zone = zoneRepo.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        LoadSheddingEvent event = LoadSheddingEvent.builder()
                .zone(zone)
                .eventStart(Instant.now())
                .reason("AUTO_LOAD_SHEDDING")
                .triggeredByForecastId(forecast.getId())
                .build();

        return repo.save(event);
    }

    @Override
    public LoadSheddingEvent getEventById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Load shedding event not found"));
    }

    @Override
    public List<LoadSheddingEvent> getEventsForZone(Long zoneId) {
        Zone zone = zoneRepo.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        return repo.findByZone(zone);
    }

    @Override
    public List<LoadSheddingEvent> getAllEvents() {
        return repo.findAll();
    }
}
