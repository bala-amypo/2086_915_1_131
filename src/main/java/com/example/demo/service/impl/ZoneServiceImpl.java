package com.example.demo.service.impl;

import com.example.demo.entity.Zone;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;

import java.util.List;

public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository repo;

    public ZoneServiceImpl(ZoneRepository repo) {
        this.repo = repo;
    }

    @Override
    public Zone createZone(Zone zone) {
        // Check if zone name already exists
        repo.findByZoneName(zone.getZoneName()).ifPresent(z -> {
            throw new BadRequestException("Zone name must be unique");
        });

        // Example additional validation
        if (zone.getZoneName() == null || zone.getZoneName().isEmpty()) {
            throw new BadRequestException("Zone name cannot be empty");
        }

        // Save the zone
        return repo.save(zone);
    }

    @Override
    public Zone getZoneById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id));
    }

    @Override
    public List<Zone> getAllZones() {
        return repo.findAll();
    }

    @Override
    public Zone updateZone(Long id, Zone zoneDetails) {
        Zone existingZone = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id));

        existingZone.setZoneName(zoneDetails.getZoneName());
        // Add other fields as necessary

        return repo.save(existingZone);
    }

    @Override
    public void deleteZone(Long id) {
        Zone existingZone = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id));
        repo.delete(existingZone);
    }
}
