package com.example.demo.service.impl;

import com.example.demo.entity.Zone;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;

import java.time.Instant;
import java.util.List;

public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepo;

    public ZoneServiceImpl(ZoneRepository zoneRepo) {
        this.zoneRepo = zoneRepo;
    }

    @Override
    public Zone createZone(Zone zone) {
        if (zone.getPriorityLevel() == null || zone.getPriorityLevel() < 1) {
            throw new BadRequestException("priority >= 1");
        }

        if (zoneRepo.findByZoneName(zone.getZoneName()).isPresent()) {
            throw new BadRequestException("unique");
        }

        zone.setActive(true);
        zone.setCreatedAt(Instant.now());
        return zoneRepo.save(zone);
    }

    @Override
    public Zone updateZone(Long id, Zone zone) {
        Zone existing = zoneRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        existing.setZoneName(zone.getZoneName());
        existing.setPriorityLevel(zone.getPriorityLevel());
        existing.setPopulation(zone.getPopulation());
        existing.setActive(zone.getActive() != null ? zone.getActive() : existing.getActive());
        existing.setUpdatedAt(Instant.now());

        return zoneRepo.save(existing);
    }

    @Override
    public Zone getZoneById(Long id) {
        return zoneRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<Zone> getAllZones() {
        return zoneRepo.findAll();
    }

    @Override
    public void deactivateZone(Long id) {
        Zone z = zoneRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        z.setActive(false);
        zoneRepo.save(z);
    }
}
