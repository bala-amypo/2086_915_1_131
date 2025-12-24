package com.example.demo.entity;

import java.time.Instant;

public class LoadSheddingEvent {
    private Long id;
    private Zone zone;
    private String reason;
    private Instant eventStart;
    private Double expectedDemandReductionMW;

    public static LoadSheddingEventBuilder builder() {
        return new LoadSheddingEventBuilder();
    }

    public static class LoadSheddingEventBuilder {
        private LoadSheddingEvent event = new LoadSheddingEvent();

        public LoadSheddingEventBuilder id(Long id) {
            event.id = id;
            return this;
        }

        public LoadSheddingEventBuilder zone(Zone zone) {
            event.zone = zone;
            return this;
        }

        public LoadSheddingEventBuilder reason(String reason) {
            event.reason = reason;
            return this;
        }

        public LoadSheddingEventBuilder eventStart(Instant eventStart) {
            event.eventStart = eventStart;
            return this;
        }

        public LoadSheddingEventBuilder expectedDemandReductionMW(Double expectedDemandReductionMW) {
            event.expectedDemandReductionMW = expectedDemandReductionMW;
            return this;
        }

        public LoadSheddingEvent build() {
            return event;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public Instant getEventStart() { return eventStart; }
    public void setEventStart(Instant eventStart) { this.eventStart = eventStart; }
    public Double getExpectedDemandReductionMW() { return expectedDemandReductionMW; }
    public void setExpectedDemandReductionMW(Double expectedDemandReductionMW) { this.expectedDemandReductionMW = expectedDemandReductionMW; }
}