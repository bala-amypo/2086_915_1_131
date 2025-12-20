package com.example.demo.repository;

import com.example.demo.entity.SupplyForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SupplyForecastRepository extends JpaRepository<SupplyForecast, Long> {
    List<SupplyForecast> findByZoneIdOrderByForecastTimeDesc(Long zoneId);
}
