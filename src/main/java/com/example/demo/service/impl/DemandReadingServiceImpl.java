package com.example.demo.service.impl;

import com.example.demo.entity.DemandReading;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.service.DemandReadingService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service  // <- THIS IS CRUCIAL
public class DemandReadingServiceImpl implements DemandReadingService {

    private final DemandReadingRepository repository;

    public DemandReadingServiceImpl(DemandReadingRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DemandReading> getAllDemandReadings() {
        return repository.findAll();
    }

    @Override
    public DemandReading getDemandReadingById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DemandReading not found"));
    }

    @Override
    public DemandReading createDemandReading(DemandReading demandReading) {
        return repository.save(demandReading);
    }

    @Override
    public void deleteDemandReading(Long id) {
        repository.deleteById(id);
    }
}
