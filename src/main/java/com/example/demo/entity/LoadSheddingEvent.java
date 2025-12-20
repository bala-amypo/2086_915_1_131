package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class LoadSheddingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Zone zone;

    private Instant eventStart;
    private Instant eventEnd;

    private String reason;

    private Long triggeredByForecastId;

    private Double expectedDemandReductionMW;

    public LoadSheddingEvent() {}

    public LoadSheddingEvent(Long id, Zone zone, Instant eventStart, Instant eventEnd,
                             String reason, Long triggeredByForecastId,
                             Double expectedDemandReductionMW) {
        this.id = id;
        this.zone = zone;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.reason = reason;
        this.triggeredByForecastId = triggeredByForecastId;
        this.expectedDemandReductionMW = expectedDemandReductionMW;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }

    public Instant getEventStart() { return eventStart; }
    public void setEventStart(Instant eventStart) { this.eventStart = eventStart; }

    public Instant getEventEnd() { return eventEnd; }
    public void setEventEnd(Instant eventEnd) { this.eventEnd = eventEnd; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Long getTriggeredByForecastId() { return triggeredByForecastId; }
    public void setTriggeredByForecastId(Long triggeredByForecastId) {
        this.triggeredByForecastId = triggeredByForecastId;
    }

    public Double getExpectedDemandReductionMW() { return expectedDemandReductionMW; }
    public void setExpectedDemandReductionMW(Double expectedDemandReductionMW) {
        this.expectedDemandReductionMW = expectedDemandReductionMW;
    }
}
