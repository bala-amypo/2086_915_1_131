package com.example.demo.service.impl;

import com.example.demo.service.LoadSheddingService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    @Override
    public List<String> getEventsForZone(Long zoneId) {
        // Temporary safe implementation
        // No repository used because none exists
        return Collections.emptyList();
    }
}
