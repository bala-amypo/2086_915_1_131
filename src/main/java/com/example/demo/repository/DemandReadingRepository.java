package com.example.demo.repository;

import com.example.demo.entity.DemandReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DemandReadingRepository extends JpaRepository<DemandReading, Long> {

    Optional<DemandReading> findFirstByZoneIdOrderByRecordedAtDesc(Long zoneId);

    List<DemandReading> findAllByZoneIdOrderByRecordedAtDesc(Long zoneId);

    @Query(value = "SELECT * FROM demand_reading WHERE zone_id = :zoneId ORDER BY recorded_at DESC LIMIT :count", nativeQuery = true)
    List<DemandReading> findTopNByZoneIdOrderByRecordedAtDesc(Long zoneId, int count);
}
