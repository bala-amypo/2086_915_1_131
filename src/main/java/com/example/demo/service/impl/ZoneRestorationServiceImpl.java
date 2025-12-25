package com.example.demo.service.impl;

import com.example.demo.entity.ZoneRestorationRecord;
import com.example.demo.repository.ZoneRestorationRepository;
import com.example.demo.service.ZoneRestorationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneRestorationServiceImpl implements ZoneRestorationService {

    private final ZoneRestorationRepository repository;

    public ZoneRestorationServiceImpl(ZoneRestorationRepository repository) {
        this.repository = repository;
    }

    @Override
    public ZoneRestorationRecord save(ZoneRestorationRecord record) {
        return repository.save(record);
    }

    @Override
    public List<ZoneRestorationRecord> getAll() {
        return repository.findAll();
    }
}
