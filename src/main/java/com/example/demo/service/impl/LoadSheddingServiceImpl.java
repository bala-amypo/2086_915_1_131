package com.example.demo.service.impl;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.Zone;
import com.example.demo.repository.*;
import com.example.demo.service.LoadSheddingService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final SupplyForecastRepository forecastRepository;
    private final ZoneRepository zoneRepository;
    private final DemandReadingRepository readingRepository;
    private final LoadSheddingEventRepository eventRepository;

    public LoadSheddingServiceImpl(
            SupplyForecastRepository forecastRepository,
            ZoneRepository zoneRepository,
            DemandReadingRepository readingRepository,
            LoadSheddingEventRepository eventRepository) {
        this.forecastRepository = forecastRepository;
        this.zoneRepository = zoneRepository;
        this.readingRepository = readingRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public LoadSheddingEvent triggerLoadShedding(Long forecastId) {

        var forecast = forecastRepository.findById(forecastId)
                .orElseThrow(() -> new RuntimeException("Forecast not found"));

        List<Zone> zones =
                zoneRepository.findByActiveTrueOrderByPriorityLevelAsc();

        if (zones.isEmpty()) {
            throw new RuntimeException("No suitable");
        }

        Zone target = zones.get(zones.size() - 1);

        var event = LoadSheddingEvent.builder()
                .zone(target)
                .eventStart(Instant.now())
                .reason("Overload")
                .triggeredByForecastId(forecast.getId())
                .expectedDemandReductionMW(50.0)
                .build();

        return eventRepository.save(event);
    }

    @Override
    public LoadSheddingEvent getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Override
    public List<LoadSheddingEvent> getEventsForZone(Long zoneId) {
        return eventRepository.findByZoneIdOrderByEventStartDesc(zoneId);
    }

    @Override
    public List<LoadSheddingEvent> getAllEvents() {
        return eventRepository.findAll();
    }
}
