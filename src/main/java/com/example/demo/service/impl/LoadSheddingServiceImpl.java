package com.example.demo.service.impl;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.Zone;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.repository.SupplyForecastRepository;
import com.example.demo.repository.ZoneRepository;
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

        // 1. Forecast must exist
        forecastRepository.findById(forecastId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Forecast not found"));

        // 2. Get active zones ordered by priority
        List<Zone> zones =
                zoneRepository.findByActiveTrueOrderByPriorityLevelAsc();

        if (zones.isEmpty()) {
            throw new BadRequestException("No suitable zones");
        }

        // 3. Pick lowest priority zone (last in sorted list)
        Zone targetZone = zones.get(zones.size() - 1);

        // 4. Create load shedding event
        LoadSheddingEvent event = LoadSheddingEvent.builder()
                .zone(targetZone)
                .eventStart(Instant.now())
                .reason("Overload")
                .triggeredByForecastId(forecastId)   // ✅ CORRECT FIELD
                .expectedDemandReductionMW(50.0)
                .build();

        return eventRepository.save(event);
    }

    @Override
    public LoadSheddingEvent getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found"));
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
