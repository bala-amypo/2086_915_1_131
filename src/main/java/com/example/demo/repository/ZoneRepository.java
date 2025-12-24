package com.example.demo.repository;

import com.example.demo.entity.Zone;
import java.util.List;
import java.util.Optional;

public interface ZoneRepository {
    Optional<Zone> findByZoneName(String zoneName);
    Zone save(Zone zone);
    Optional<Zone> findById(Long id);
    List<Zone> findAll();
    List<Zone> findByActiveTrueOrderByPriorityLevelAsc();
}