package com.example.demo.service.impl;

import com.example.demo.entity.Zone;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;

import java.time.Instant;
import java.util.List;

public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository repo;

    public ZoneServiceImpl(ZoneRepository repo) {
        this.repo = repo;
    }

    @Override
    public Zone createZone(Zone zone) {

        repo.findByZoneName(zone.getZoneName()).ifPresent(z -> {
            throw new BadRequestException("unique");
        });

        if (zone.
