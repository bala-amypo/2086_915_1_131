package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "demand_readings")
public class DemandReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;

    @Column(nullable = false)
    private Double demandMW;

    @Column(nullable = false)
    private Instant recordedAt;

    public DemandReading() {}

    public static DemandReadingBuilder builder() {
        return new DemandReadingBuilder();
    }

    public static class DemandReadingBuilder {
        private final DemandReading reading = new DemandReading();

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
    public Zone getZone() { return zone; }
    public Double getDemandMW() { return demandMW; }
    public Instant getRecordedAt() { return recordedAt; }
}
