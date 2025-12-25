package com.example.demo.repository;

import com.example.demo.entity.DemandReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface DemandReadingRepository extends JpaRepository<DemandReading, Long> {

    DemandReading findTopByZoneIdOrderByRecordedAtDesc(Long zoneId);

    List<DemandReading> findByZoneId(Long zoneId);

    @Query(value = "SELECT * FROM demand_reading WHERE zone_id = ?1 ORDER BY recorded_at DESC LIMIT ?2", nativeQuery = true)
    List<DemandReading> findRecentByZoneId(Long zoneId, int limit);
}
