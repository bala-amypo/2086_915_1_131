package com.example.demo.service.impl;

import com.example.demo.entity.Zone;
import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.ZoneRestorationRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.repository.ZoneRestorationRecordRepository;
import com.example.demo.service.ZoneRestorationService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Service
public class ZoneRestorationServiceImpl implements ZoneRestorationService {

    private final ZoneRestorationRecordRepository restorationRepository;
    private final LoadSheddingEventRepository eventRepository;
    private final ZoneRepository zoneRepository;

    public ZoneRestorationServiceImpl(
            ZoneRestorationRecordRepository restorationRepository,
            LoadSheddingEventRepository eventRepository,
            ZoneRepository zoneRepository) {

        this.restorationRepository = restorationRepository;
        this.eventRepository = eventRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public ZoneRestorationRecord restoreZone(ZoneRestorationRecord record) {

        LoadSheddingEvent event = eventRepository.findById(record.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        Zone zone = zoneRepository.findById(record.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        Instant eventInstant = event.getEventStart()
                .atZone(ZoneId.systemDefault())
                .toInstant();

        if (!record.getRestoredAt().isAfter(eventInstant)) {
            throw new BadRequestException("Restoration time must be after event start time");
        }

        record.setZone(zone);
        return restorationRepository.save(record);
    }

    @Override
    public ZoneRestorationRecord getRecordById(Long id) {
        return restorationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
    }

    @Override
    public List<ZoneRestorationRecord> getRecordsForZone(Long zoneId) {
        return restorationRepository.findByZoneIdOrderByRestoredAtDesc(zoneId);
    }
}
