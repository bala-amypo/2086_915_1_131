package com.example.demo.service.impl;

import com.example.demo.entity.DemandReading;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.service.DemandReadingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandReadingServiceImpl implements DemandReadingService {

    private final DemandReadingRepository demandReadingRepository;

    public DemandReadingServiceImpl(DemandReadingRepository demandReadingRepository) {
        this.demandReadingRepository = demandReadingRepository;
    }

    @Override
    public DemandReading saveDemandReading(DemandReading demandReading) {
        return demandReadingRepository.save(demandReading);
    }

    @Override
    public List<DemandReading> getAllDemandReadings() {
        return demandReadingRepository.findAll();
    }

    @Override
    public Optional<DemandReading> getDemandReadingById(Long id) {
        return demandReadingRepository.findById(id);
    }

    @Override
    public void deleteDemandReading(Long id) {
        demandReadingRepository.deleteById(id);
    }
}
