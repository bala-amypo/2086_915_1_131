package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LoadSheddingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime eventStart;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    // ✅ REQUIRED by JPA
    public LoadSheddingEvent() {
    }

    // ✅ Convenience constructor
    public LoadSheddingEvent(LocalDateTime eventStart, Zone zone) {
        this.eventStart = eventStart;
        this.zone = zone;
    }

    // ===== Getters =====

    public Long getId() {
        return id;
    }

    public LocalDateTime getEventStart() {
        return eventStart;
    }

    public Zone getZone() {
        return zone;
    }

    // ===== Setters =====

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventStart(LocalDateTime eventStart) {
        this.eventStart = eventStart;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
