package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class SupplyForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long zoneId;

    private Instant forecastTime;

    private double supplyValue;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public Instant getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(Instant forecastTime) {
        this.forecastTime = forecastTime;
    }

    public double getSupplyValue() {
        return supplyValue;
    }

    public void setSupplyValue(double supplyValue) {
        this.supplyValue = supplyValue;
    }
}
