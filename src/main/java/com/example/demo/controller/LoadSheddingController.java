package com.example.demo.controller;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.service.LoadSheddingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/load-shedding")
public class LoadSheddingController {

    private final LoadSheddingService sheddingService;

    public LoadSheddingController(LoadSheddingService sheddingService) {
        this.sheddingService = sheddingService;
    }

    @PostMapping("/trigger/{forecastId}")
    public ResponseEntity<LoadSheddingEvent> triggerLoadShedding(@PathVariable Long forecastId) {
        return ResponseEntity.ok(sheddingService.triggerLoadShedding(forecastId));
    }

    @GetMapping("/events")
    public ResponseEntity<List<LoadSheddingEvent>> getAllEvents() {
        return ResponseEntity.ok(sheddingService.getAllEvents());
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<LoadSheddingEvent> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(sheddingService.getEventById(id));
    }

    @GetMapping("/zone/{zoneId}")
    public ResponseEntity<List<LoadSheddingEvent>> getEventsForZone(@PathVariable Long zoneId) {
        return ResponseEntity.ok(sheddingService.getEventsForZone(zoneId));
    }
}
