package com.example.demo.service;

import com.example.demo.entity.LoadSheddingEvent;

import java.util.List;

public interface LoadSheddingService {

    List<LoadSheddingEvent> getAllEvents();

    LoadSheddingEvent triggerLoadShedding(Long zoneId);

    LoadSheddingEvent getEventById(Long eventId);

    List<LoadSheddingEvent> getEventsForZone(Long zoneId);
}
