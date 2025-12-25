package com.example.demo.service;

import com.example.demo.dto.ZoneDTO;

import java.util.List;

public interface ZoneService {

    ZoneDTO createZone(ZoneDTO zoneDTO);

    ZoneDTO getZoneById(Long id);

    List<ZoneDTO> getAllZones();

    ZoneDTO updateZone(Long id, ZoneDTO zoneDTO);

    void deleteZone(Long id);
}
