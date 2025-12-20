package com.example.demo.repository;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoadSheddingEventRepository extends JpaRepository<LoadSheddingEvent, Long> {

    List<LoadSheddingEvent> findByZone(Zone zone);
}
