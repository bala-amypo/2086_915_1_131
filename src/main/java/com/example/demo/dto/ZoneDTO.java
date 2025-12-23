package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZoneDTO {
    private Long id;
    private String zoneName;
    private Integer priorityLevel;
    private Integer population;
    private Boolean active;
}
