package com.example.demo.service;

import com.example.demo.entity.DemandReading;
import java.util.List;

public interface DemandReadingService {
    List<DemandReading> getAllDemandReadings();
    DemandReading getDemandReadingById(Long id);
    DemandReading createDemandReading(DemandReading demandReading);
    void deleteDemandReading(Long id);
}
