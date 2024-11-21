package com.example.backend_TrailSync.Application.boots.Resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBootResource {
    private Long id;
    private Long code;
    private String state;
    private Integer batery;
    private Long steps;
    private Double latitude;
    private Double longitude;
    private Double distance;
    private Long heartRate;
    private Float temperature;
}
