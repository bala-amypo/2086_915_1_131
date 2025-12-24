package com.example.demo.entity;

import java.time.Instant;

public class ZoneRestorationRecord {
    private Long id;
    private Zone zone;
    private Long eventId;
    private Instant restoredAt;
    private String notes;

    public static ZoneRestorationRecordBuilder builder() {
        return new ZoneRestorationRecordBuilder();
    }

    public static class ZoneRestorationRecordBuilder {
        private ZoneRestorationRecord record = new ZoneRestorationRecord();

        public ZoneRestorationRecordBuilder id(Long id) {
            record.id = id;
            return this;
        }

        public ZoneRestorationRecordBuilder zone(Zone zone) {
            record.zone = zone;
            return this;
        }

        public ZoneRestorationRecordBuilder eventId(Long eventId) {
            record.eventId = eventId;
            return this;
        }

        public ZoneRestorationRecordBuilder restoredAt(Instant restoredAt) {
            record.restoredAt = restoredAt;
            return this;
        }

        public ZoneRestorationRecordBuilder notes(String notes) {
            record.notes = notes;
            return this;
        }

        public ZoneRestorationRecord build() {
            return record;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    public Instant getRestoredAt() { return restoredAt; }
    public void setRestoredAt(Instant restoredAt) { this.restoredAt = restoredAt; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}