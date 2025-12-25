package com.example.demo.service;

import com.example.demo.entity.LoadSheddingEvent;
import java.util.List;

public interface LoadSheddingService {

    void triggerLoadShedding(Long zoneId);

    List<LoadSheddingEvent> getAllEvents();

    LoadSheddingEvent getEventById(Long eventId);

    List<LoadSheddingEvent> getEventsForZone(Long zoneId);
}
