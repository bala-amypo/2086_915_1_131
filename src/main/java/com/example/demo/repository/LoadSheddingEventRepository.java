package com.example.demo.repository;

import com.example.demo.entity.LoadSheddingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadSheddingEventRepository extends JpaRepository<LoadSheddingEvent, Long> {
}
