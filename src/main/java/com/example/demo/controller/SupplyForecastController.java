package com.example.demo.controller;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.service.SupplyForecastService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/forecasts")
public class SupplyForecastController {

    private final SupplyForecastService service;

    public SupplyForecastController(SupplyForecastService service) {
        this.service = service;
    }

    @PostMapping
    public SupplyForecast createForecast(@RequestBody SupplyForecast forecast) {
        return service.createForecast(forecast);
    }

    @GetMapping("/zone/{zoneId}")
    public List<SupplyForecast> getForecastsForZone(@PathVariable Long zoneId) {
        return service.getForecastsForZone(zoneId);
    }
}
