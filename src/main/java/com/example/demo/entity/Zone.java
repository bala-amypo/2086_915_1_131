package com.example.demo.entity;

import java.time.Instant;

public class Zone {
    private Long id;
    private String zoneName;
    private Integer priorityLevel;
    private Integer population;
    private Boolean active = true;
    private Instant createdAt = Instant.now();
    private Instant updatedAt;

    public static ZoneBuilder builder() {
        return new ZoneBuilder();
    }

    public static class ZoneBuilder {
        private Zone zone = new Zone();

        public ZoneBuilder id(Long id) {
            zone.id = id;
            return this;
        }

        public ZoneBuilder zoneName(String zoneName) {
            zone.zoneName = zoneName;
            return this;
        }

        public ZoneBuilder priorityLevel(Integer priorityLevel) {
            zone.priorityLevel = priorityLevel;
            return this;
        }

        public ZoneBuilder population(Integer population) {
            zone.population = population;
            return this;
        }

        public ZoneBuilder active(Boolean active) {
            zone.active = active;
            return this;
        }

        public Zone build() {
            return zone;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getZoneName() { return zoneName; }
    public void setZoneName(String zoneName) { this.zoneName = zoneName; }
    public Integer getPriorityLevel() { return priorityLevel; }
    public void setPriorityLevel(Integer priorityLevel) { this.priorityLevel = priorityLevel; }
    public Integer getPopulation() { return population; }
    public void setPopulation(Integer population) { this.population = population; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}