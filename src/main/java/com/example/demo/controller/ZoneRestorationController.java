package com.example.demo.controller;

import com.example.demo.entity.ZoneRestorationRecord;
import com.example.demo.service.ZoneRestorationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restorations")
public class ZoneRestorationController {

    private final ZoneRestorationService restorationService;

    public ZoneRestorationController(ZoneRestorationService restorationService) {
        this.restorationService = restorationService;
    }

    @PostMapping
    public ResponseEntity<ZoneRestorationRecord> restoreZone(@RequestBody ZoneRestorationRecord record) {
        return ResponseEntity.ok(restorationService.restoreZone(record));
    }

    @GetMapping("/zone/{zoneId}")
    public ResponseEntity<List<ZoneRestorationRecord>> getRecordsForZone(@PathVariable Long zoneId) {
        return ResponseEntity.ok(restorationService.getRecordsForZone(zoneId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZoneRestorationRecord> getRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(restorationService.getRecordById(id));
    }
}
