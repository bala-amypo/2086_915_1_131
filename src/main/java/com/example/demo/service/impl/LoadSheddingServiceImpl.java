package com.example.demo.service.impl;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.service.LoadSheddingService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final LoadSheddingEventRepository repository;

    public LoadSheddingServiceImpl(LoadSheddingEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoadSheddingEvent triggerLoadShedding(Long zoneId) {
        LoadSheddingEvent event = new LoadSheddingEvent();
        event.setZoneId(zoneId);
        event.setStatus("SHEDDING");
        event.setCreatedAt(Instant.now());
        return repository.save(event);
    }

    @Override
    public List<LoadSheddingEvent> getAllEvents() {
        return repository.findAll();
    }

    @Override
    public LoadSheddingEvent getEventById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
