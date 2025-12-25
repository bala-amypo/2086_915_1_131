package com.example.demo.service;

import com.example.demo.entity.SupplyForecast;

import java.util.List;

public interface SupplyForecastService {

    SupplyForecast save(SupplyForecast supplyForecast);

    List<SupplyForecast> getAll();
}
