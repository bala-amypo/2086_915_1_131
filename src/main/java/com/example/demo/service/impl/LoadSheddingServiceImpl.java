package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.LoadSheddingService;

import java.time.Instant;
import java.util.List;

public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final SupplyForecastRepository forecastRepo;
    private final ZoneRepository zoneRepo;
    private final DemandReadingRepository readingRepo;
    private final LoadSheddingEventRepository eventRepo;

    public LoadSheddingServiceImpl(
            SupplyForecastRepository forecastRepo,
            ZoneRepository zoneRepo,
            DemandReadingRepository readingRepo,
            LoadSheddingEventRepository eventRepo
    ) {
        this.forecastRepo = forecastRepo;
        this.zoneRepo = zoneRepo;
        this.readingRepo = readingRepo;
        this.eventRepo = eventRepo;
    }

    @Override
    public LoadSheddingEvent triggerLoadShedding(Long forecastId) {
        SupplyForecast forecast = forecastRepo.findById(forecastId)
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));

        List<Zone> zones = zoneRepo.findByActiveTrueOrderByPriorityLevelAsc();
        if (zones.isEmpty()) {
            throw new BadRequestException("No overload");
        }

        for (Zone z : zones) {
            var readingOpt = readingRepo
                    .findFirstByZoneIdOrderByRecordedAtDesc(z.getId());

            if (readingOpt.isPresent() &&
                readingOpt.get().getDemandMW() > forecast.getAvailableSupplyMW()) {

                LoadSheddingEvent ev = LoadSheddingEvent.builder()
                        .zone(z)
                        .eventStart(Instant.now())
                        .expectedDemandReductionMW(readingOpt.get().getDemandMW())
                        .reason("Overload")
                        .build();

                return eventRepo.save(ev);
            }
        }

        throw new BadRequestException("No overload");
    }

    @Override
    public LoadSheddingEvent getEventById(Long id) {
        return eventRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    @Override
    public List<LoadSheddingEvent> getAllEvents() {
        return eventRepo.findAll();
    }

    @Override
    public List<LoadSheddingEvent> getEventsForZone(Long zoneId) {
        return eventRepo.findByZoneIdOrderByEventStartDesc(zoneId);
    }
}
