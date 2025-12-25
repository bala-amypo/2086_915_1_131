package com.example.demo.repository;

import com.example.demo.entity.DemandReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemandReadingRepository extends JpaRepository<DemandReading, Long> {

    @Query("""
        SELECT d FROM DemandReading d
        WHERE d.zone.id = :zoneId
        ORDER BY d.recordedAt DESC
    """)
    List<DemandReading> findTopByZoneIdOrderByRecordedAtDesc(
            @Param("zoneId") Long zoneId,
            @Param("limit") int limit
    );
}
