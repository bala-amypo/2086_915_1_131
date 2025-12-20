package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class DemandReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Zone zone;

    private Double demandMW;

    private Instant recordedAt;

    public DemandReading() {}

    public DemandReading(Long id, Zone zone, Double demandMW, Instant recordedAt) {
        this.id = id;
        this.zone = zone;
        this.demandMW = demandMW;
        this.recordedAt = recordedAt;
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
