package com.example.demo.controller;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.service.SupplyForecastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forecasts")
public class SupplyForecastController {

    private final SupplyForecastService forecastService;

    public SupplyForecastController(SupplyForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @PostMapping
    public ResponseEntity<SupplyForecast> createForecast(@RequestBody SupplyForecast forecast) {
        return ResponseEntity.ok(forecastService.createForecast(forecast));
    }

    @GetMapping("/latest")
    public ResponseEntity<SupplyForecast> getLatestForecast() {
        return ResponseEntity.ok(forecastService.getLatestForecast());
    }

    @GetMapping
    public ResponseEntity<List<SupplyForecast>> getAllForecasts() {
        return ResponseEntity.ok(forecastService.getAllForecasts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplyForecast> updateForecast(@PathVariable Long id, @RequestBody SupplyForecast forecast) {
        return ResponseEntity.ok(forecastService.updateForecast(id, forecast));
    }
}
