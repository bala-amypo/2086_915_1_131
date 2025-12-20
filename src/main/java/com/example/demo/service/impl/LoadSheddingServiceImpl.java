package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.LoadSheddingService;

import java.time.Instant;
import java.util.List;

public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final SupplyForecastRepository supplyForecastRepository;
    private final ZoneRepository zoneRepository;
    private final DemandReadingRepository demandReadingRepository;
    private final LoadSheddingEventRepository loadSheddingEventRepository;

    public LoadSheddingServiceImpl(SupplyForecastRepository supplyForecastRepository,
                                   ZoneRepository zoneRepository,
                                   DemandReadingRepository demandReadingRepository,
                                   LoadSheddingEventRepository loadSheddingEventRepository) {
        this.supplyForecastRepository = supplyForecastRepository;
        this.zoneRepository = zoneRepository;
        this.demandReadingRepository = demandReadingRepository;
        this.loadSheddingEventRepository = loadSheddingEventRepository;
    }

    @Override
    public LoadSheddingEvent triggerLoadShedding(Long forecastId) {
        SupplyForecast forecast = supplyForecastRepository.findById(forecastId)
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));

        List<Zone> zones = zoneRepository.findByActiveTrueOrderByPriorityLevelAsc();
        if (zones.isEmpty()) {
            throw new BadRequestException("No suitable zones");
        }

        Zone zone = zones.get(0);
        DemandReading reading = demandReadingRepository
                .findFirstByZoneIdOrderByRecordedAtDesc(zone.getId())
                .orElseThrow(() -> new BadRequestException("No overload"));

        LoadSheddingEvent event = new LoadSheddingEvent();
        event.setZone(zone);
        event.setEventStart(Instant.now());
        event.setReason("Overload");
        event.setTriggeredByForecastId(forecastId);
        event.setExpectedDemandReductionMW(reading.getDemandMW());

        return loadSheddingEventRepository.save(event);
    }

    @Override
    public LoadSheddingEvent getEventById(Long id) {
        return loadSheddingEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    @Override
    public List<LoadSheddingEvent> getEventsForZone(Long zoneId) {
        return loadSheddingEventRepository.findByZoneIdOrderByEventStartDesc(zoneId);
    }

    @Override
    public List<LoadSheddingEvent> getAllEvents() {
        return loadSheddingEventRepository.findAll();
    }
}
