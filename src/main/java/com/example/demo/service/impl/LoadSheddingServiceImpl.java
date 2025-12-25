package com.example.demo.service.impl;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.service.LoadSheddingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final LoadSheddingEventRepository repository;

    public LoadSheddingServiceImpl(LoadSheddingEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoadSheddingEvent save(LoadSheddingEvent event) {
        return repository.save(event);
    }

    @Override
    public List<LoadSheddingEvent> getAll() {
        return repository.findAll();
    }
}
