package com.example.demo.entity;

import java.time.Instant;

public class SupplyForecast {
    private Long id;
    private Double availableSupplyMW;
    private Instant forecastStart;
    private Instant forecastEnd;
    private Instant generatedAt = Instant.now();

    public static SupplyForecastBuilder builder() {
        return new SupplyForecastBuilder();
    }

    public static class SupplyForecastBuilder {
        private SupplyForecast forecast = new SupplyForecast();

        public SupplyForecastBuilder id(Long id) {
            forecast.id = id;
            return this;
        }

        public SupplyForecastBuilder availableSupplyMW(Double availableSupplyMW) {
            forecast.availableSupplyMW = availableSupplyMW;
            return this;
        }

        public SupplyForecastBuilder forecastStart(Instant forecastStart) {
            forecast.forecastStart = forecastStart;
            return this;
        }

        public SupplyForecastBuilder forecastEnd(Instant forecastEnd) {
            forecast.forecastEnd = forecastEnd;
            return this;
        }

        public SupplyForecast build() {
            return forecast;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getAvailableSupplyMW() { return availableSupplyMW; }
    public void setAvailableSupplyMW(Double availableSupplyMW) { this.availableSupplyMW = availableSupplyMW; }
    public Instant getForecastStart() { return forecastStart; }
    public void setForecastStart(Instant forecastStart) { this.forecastStart = forecastStart; }
    public Instant getForecastEnd() { return forecastEnd; }
    public void setForecastEnd(Instant forecastEnd) { this.forecastEnd = forecastEnd; }
    public Instant getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(Instant generatedAt) { this.generatedAt = generatedAt; }
}