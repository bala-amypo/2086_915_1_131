package com.example.demo.repository;

import com.example.demo.entity.DemandReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DemandReadingRepository extends JpaRepository<DemandReading, Long> {

    // Added method to get the latest reading by zone
    Optional<DemandReading> findFirstByZoneIdOrderByRecordedAtDesc(Long zoneId);
}
