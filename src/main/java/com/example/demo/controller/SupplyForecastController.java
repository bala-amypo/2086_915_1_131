package com.example.demo.controller;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.service.SupplyForecastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forecast")
public class SupplyForecastController {

    private final SupplyForecastService supplyForecastService;

    public SupplyForecastController(SupplyForecastService supplyForecastService) {
        this.supplyForecastService = supplyForecastService;
    }

    // Create a new forecast
    @PostMapping
    public ResponseEntity<SupplyForecast> createForecast(@RequestBody SupplyForecast forecast) {
        SupplyForecast created = supplyForecastService.createForecast(forecast);
        return ResponseEntity.ok(created);
    }

    // Get all forecasts for a specific zone
    @GetMapping("/zone/{zoneId}")
    public ResponseEntity<List<SupplyForecast>> getForecastsForZone(@PathVariable Long zoneId) {
        List<SupplyForecast> forecasts = supplyForecastService.getForecastsForZone(zoneId);
        return ResponseEntity.ok(forecasts);
    }

    // Get the latest forecast for a zone
    @GetMapping("/zone/{zoneId}/latest")
    public ResponseEntity<SupplyForecast> getLatestForecast(@PathVariable Long zoneId) {
        SupplyForecast latest = supplyForecastService.getLatestForecast(zoneId);
        return ResponseEntity.ok(latest);
    }
}
