package com.example.demo.service.impl;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.service.LoadSheddingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final List<LoadSheddingEvent> events = new ArrayList<>();

    @Override
    public LoadSheddingEvent triggerLoadShedding(Long zoneId) {
        LoadSheddingEvent event = new LoadSheddingEvent();
        event.setZoneId(zoneId);
        event.setStatus("TRIGGERED");
        events.add(event);
        return event;
    }

    @Override
    public List<LoadSheddingEvent> getAllEvents() {
        return events;
    }

    @Override
    public LoadSheddingEvent getEventById(Long id) {
        return events.stream()
                .filter(e -> id.equals(e.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<LoadSheddingEvent> getEventsForZone(Long zoneId) {
        return events.stream()
                .filter(e -> zoneId.equals(e.getZoneId()))
                .toList();
    }
}
