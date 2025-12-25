package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZoneDTO {

    private Long id;
    private String zoneName;
    private Integer priorityLevel;
    private Integer population;
    private Boolean active;
}
