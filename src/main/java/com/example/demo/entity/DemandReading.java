package com.example.demo.entity;

import java.time.Instant;

public class DemandReading {
    private Long id;
    private Zone zone;
    private Double demandMW;
    private Instant recordedAt;

    public static DemandReadingBuilder builder() {
        return new DemandReadingBuilder();
    }

    public static class DemandReadingBuilder {
        private DemandReading reading = new DemandReading();

        public DemandReadingBuilder id(Long id) {
            reading.id = id;
            return this;
        }

        public DemandReadingBuilder zone(Zone zone) {
            reading.zone = zone;
            return this;
        }

        public DemandReadingBuilder demandMW(Double demandMW) {
            reading.demandMW = demandMW;
            return this;
        }

        public DemandReadingBuilder recordedAt(Instant recordedAt) {
            reading.recordedAt = recordedAt;
            return this;
        }

        public DemandReading build() {
            return reading;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }
    public Double getDemandMW() { return demandMW; }
    public void setDemandMW(Double demandMW) { this.demandMW = demandMW; }
    public Instant getRecordedAt() { return recordedAt; }
    public void setRecordedAt(Instant recordedAt) { this.recordedAt = recordedAt; }
}