package com.example.demo.service.impl;

import com.example.demo.entity.Zone;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository repo;

    public ZoneServiceImpl(ZoneRepository repo) {
        this.repo = repo;
    }

    @Override
    public Zone createZone(Zone zone) {
        repo.findByZoneName(zone.getZoneName())
                .ifPresent(z -> {
                    throw new BadRequestException("Zone already exists");
                });
        return repo.save(zone);
    }

    @Override
    public Zone updateZone(Long id, Zone zone) {
        Zone existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        existing.setZoneName(zone.getZoneName());
        existing.setPriorityLevel(zone.getPriorityLevel());
        existing.setPopulation(zone.getPopulation());

        return repo.save(existing);
    }

    @Override
    public Zone getZoneById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
    }

    @Override
    public List<Zone> getAllZones() {
        return repo.findAll();
    }

    @Override
    public void deactivateZone(Long id) {
        Zone zone = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        zone.setActive(false);
        repo.save(zone);
    }
}
