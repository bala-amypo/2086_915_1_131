package com.example.demo.controller;

import com.example.demo.dto.ZoneDTO;
import com.example.demo.entity.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @PostMapping
    public ZoneDTO createZone(@RequestBody ZoneDTO dto) {
        Zone created = zoneService.createZone(toEntity(dto));
        return toDTO(created);
    }

    @PutMapping("/{id}")
    public ZoneDTO updateZone(@PathVariable Long id, @RequestBody ZoneDTO dto) {
        Zone updated = zoneService.updateZone(id, toEntity(dto));
        return toDTO(updated);
    }

    @GetMapping("/{id}")
    public ZoneDTO getZone(@PathVariable Long id) {
        return toDTO(zoneService.getZoneById(id));
    }

    @GetMapping
    public List<ZoneDTO> getAllZones() {
        return zoneService.getAllZones()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateZone(@PathVariable Long id) {
        zoneService.deactivateZone(id);
    }

    // -------------------------
    // DTO ↔ Entity MAPPERS
    // -------------------------

    private Zone toEntity(ZoneDTO dto) {
        return Zone.builder()
                .id(dto.getId())
                .zoneName(dto.getZoneName())
                .priorityLevel(dto.getPriorityLevel())
                .population(dto.getPopulation())
                .active(dto.getActive())
                .build();
    }

    private ZoneDTO toDTO(Zone zone) {
        ZoneDTO dto = new ZoneDTO();
        dto.setId(zone.getId());
        dto.setZoneName(zone.getZoneName());
        dto.setPriorityLevel(zone.getPriorityLevel());
        dto.setPopulation(zone.getPopulation());
        dto.setActive(zone.getActive());
        dto.setCreatedAt(zone.getCreatedAt());
        dto.setUpdatedAt(zone.getUpdatedAt());
        return dto;
    }
}
