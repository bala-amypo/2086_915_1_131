package com.example.demo.repository;

import com.example.demo.entity.DemandReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DemandReadingRepository extends JpaRepository<DemandReading, Long> {

    Optional<DemandReading> findTopByZoneIdOrderByRecordedAtDesc(Long zoneId);

    List<DemandReading> findByZoneId(Long zoneId);

    @Query(value = """
        SELECT * FROM demand_readings 
        WHERE zone_id = :zoneId 
        ORDER BY recorded_at DESC 
        LIMIT :limit
        """, nativeQuery = true)
    List<DemandReading> findRecentByZoneId(Long zoneId, int limit);
}