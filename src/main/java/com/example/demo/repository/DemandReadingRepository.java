package com.example.demo.repository;

import com.example.demo.entity.DemandReading;
import com.example.demo.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DemandReadingRepository extends JpaRepository<DemandReading, Long> {

    List<DemandReading> findByZoneOrderByRecordedAtDesc(Zone zone);

    Optional<DemandReading> findFirstByZoneOrderByRecordedAtDesc(Zone zone);
}
