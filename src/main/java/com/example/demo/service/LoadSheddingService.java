package com.example.demo.service;

import java.util.List;

public interface LoadSheddingService {
    List<String> getEventsForZone(Long zoneId);
}
