package com.example.demo.repository;

import com.example.demo.entity.DemandReading;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DemandReadingRepository extends JpaRepository<DemandReading, Long> {

    DemandReading findTopByZone_IdOrderByRecordedAtDesc(Long zoneId);

    List<DemandReading> findByZone_Id(Long zoneId);
}
