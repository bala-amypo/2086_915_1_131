package com.example.demo.controller;

import com.example.demo.entity.DemandReading;
import com.example.demo.service.DemandReadingService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/demand")
public class DemandReadingController {

    private final DemandReadingService service;

    public DemandReadingController(DemandReadingService service) {
        this.service = service;
    }

    @GetMapping
    public List<DemandReading> getAll() {
        return service.getAllDemandReadings();
    }

    @GetMapping("/{id}")
    public DemandReading getById(@PathVariable Long id) {
        return service.getDemandReadingById(id);
    }

    @PostMapping
    public DemandReading create(@RequestBody DemandReading demandReading) {
        return service.createDemandReading(demandReading);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteDemandReading(id);
    }
}
