package com.example.demo.service.impl;

import com.example.demo.entity.ZoneRestorationRecord;
import com.example.demo.repository.*;
import com.example.demo.service.ZoneRestorationService;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

        var event = eventRepository.findById(record.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        zoneRepository.findById(record.getZone().getId())
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        if (record.getRestoredAt()
                .isBefore(event.getEventStart())) {
            throw new RuntimeException("after event start");
        }

        return restorationRepository.save(record);
    }

    @Override
    public ZoneRestorationRecord getRecordById(Long id) {
        return restorationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }

    @Override
    public List<ZoneRestorationRecord> getRecordsForZone(Long zoneId) {
        return restorationRepository
                .findByZoneIdOrderByRestoredAtDesc(zoneId);
    }
}
