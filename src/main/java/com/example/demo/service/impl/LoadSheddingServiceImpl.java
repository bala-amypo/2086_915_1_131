package com.example.demo.service.impl;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.repository.LoadSheddingRepository;
import com.example.demo.service.LoadSheddingService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final LoadSheddingRepository loadSheddingRepository;

    public LoadSheddingServiceImpl(LoadSheddingRepository loadSheddingRepository) {
        this.loadSheddingRepository = loadSheddingRepository;
    }

    @Override
    public void triggerLoadShedding(Long zoneId) {
        // implementation
    }

    @Override
    public List<LoadSheddingEvent> getAllEvents() {
        return loadSheddingRepository.findAll();
    }

    @Override
    public LoadSheddingEvent getEventById(Long eventId) {
        return loadSheddingRepository.findById(eventId).orElse(null);
    }

    @Override
    public List<LoadSheddingEvent> getEventsForZone(Long zoneId) {
        return loadSheddingRepository.findByZoneId(zoneId);
    }
}
