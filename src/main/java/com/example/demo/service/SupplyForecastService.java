package com.example.demo.service;

import com.example.demo.entity.SupplyForecast;
import java.util.List;

public interface SupplyForecastService {
    SupplyForecast createForecast(SupplyForecast forecast);
    List<SupplyForecast> getForecastsForZone(Long zoneId);
}
