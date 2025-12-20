package com.example.demo.repository;

import com.example.demo.entity.SupplyForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SupplyForecastRepository extends JpaRepository<SupplyForecast, Long> {

    List<SupplyForecast> findByZoneIdOrderByForecastTimeDesc(Long zoneId);

    Optional<SupplyForecast> findFirstByZoneIdOrderByForecastTimeDesc(Long zoneId);

}
