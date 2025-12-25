package com.example.demo.service.impl;

import com.example.demo.service.LoadSheddingService;
import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.repository.LoadSheddingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final LoadSheddingRepository loadSheddingRepository;

    public LoadSheddingServiceImpl(LoadSheddingRepository loadSheddingRepository) {
        this.loadSheddingRepository = loadSheddingRepository;
    }

    @Override
    public LoadSheddingEvent createEvent(LoadSheddingEvent event) {
        return loadSheddingRepository.save(event);
    }

    @Override
    public List<LoadSheddingEvent> getEventsForZone(Long zoneId) {
        return loadSheddingRepository.findByZoneId(zoneId);
    }
}
