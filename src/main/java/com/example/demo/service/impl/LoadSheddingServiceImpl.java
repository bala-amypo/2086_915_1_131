package com.example.demo.service.impl;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.DemandReading;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.service.LoadSheddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        // Dummy implementation, replace with actual DB logic if needed
        return new ArrayList<>();
    }

    @Override
    public LoadSheddingEvent triggerLoadShedding(Long zoneId) {
        // Dummy implementation, replace with actual logic
        LoadSheddingEvent event = new LoadSheddingEvent();
        event.setZoneId(zoneId);
        event.setDescription("Load shedding triggered for zone " + zoneId);
        return event;
    }

    @Override
    public LoadSheddingEvent getEventById(Long eventId) {
        // Dummy implementation
        LoadSheddingEvent event = new LoadSheddingEvent();
        event.setId(eventId);
        event.setDescription("Event " + eventId);
        return event;
    }

    @Override
    public List<LoadSheddingEvent> getEventsForZone(Long zoneId) {
        // Dummy implementation
        List<LoadSheddingEvent> events = new ArrayList<>();
        events.add(triggerLoadShedding(zoneId));
        return events;
    }

    public DemandReading getLatestDemandReadingByZone(Long zoneId) {
        return demandReadingRepository
                .findFirstByZoneIdOrderByRecordedAtDesc(zoneId)
                .orElse(null);
    }
}
