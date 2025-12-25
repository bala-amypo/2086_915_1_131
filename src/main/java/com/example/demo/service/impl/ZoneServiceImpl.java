package com.example.demo.service.impl;

import com.example.demo.dto.ZoneDTO;
import com.example.demo.entity.Zone;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public ZoneDTO createZone(ZoneDTO zoneDTO) {
        Zone zone = new Zone();
        zone.setZoneName(zoneDTO.getZoneName());
        zone.setPriorityLevel(zoneDTO.getPriorityLevel());
        zone.setPopulation(zoneDTO.getPopulation());
        zone.setActive(zoneDTO.getActive());
        return mapToDTO(zoneRepository.save(zone));
    }

    @Override
    public ZoneDTO getZoneById(Long id) {
        return mapToDTO(
                zoneRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Zone not found with id: " + id))
        );
    }

    @Override
    public List<ZoneDTO> getAllZones() {
        return zoneRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ZoneDTO updateZone(Long id, ZoneDTO zoneDTO) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Zone not found with id: " + id));

        zone.setZoneName(zoneDTO.getZoneName());
        zone.setPriorityLevel(zoneDTO.getPriorityLevel());
        zone.setPopulation(zoneDTO.getPopulation());
        zone.setActive(zoneDTO.getActive());

        return mapToDTO(zoneRepository.save(zone));
    }

    @Override
    public void deleteZone(Long id) {
        zoneRepository.delete(
                zoneRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Zone not found with id: " + id))
        );
    }

    private ZoneDTO mapToDTO(Zone zone) {
        return new ZoneDTO(
                zone.getId(),
                zone.getZoneName(),
                zone.getPriorityLevel(),
                zone.getPopulation(),
                zone.getActive()
        );
    }
}
