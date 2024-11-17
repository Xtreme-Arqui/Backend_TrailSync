package com.example.backend_TrailSync.Application.boots.Resource;

import com.example.backend_TrailSync.Application.services.domain.model.entity.Service;
import com.example.backend_TrailSync.Application.tourists.domain.model.entity.Tourist;
public class BootResource {
    private Long id;
    private Long code;
    private String state;
    private Integer batery;
    private Long steps;
    private Double latitude;
    private Double longitude;
    private Double distance;
    private Long heartRate;
    //Relationships
    private Tourist tourist;
    private Service service;
}