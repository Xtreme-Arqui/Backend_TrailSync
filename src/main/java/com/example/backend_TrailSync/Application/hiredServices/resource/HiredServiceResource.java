package com.example.backend_TrailSync.Application.hiredServices.resource;

import com.example.backend_TrailSync.Application.services.resource.ServiceResource;
import com.example.backend_TrailSync.Application.tourists.resource.TouristResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class HiredServiceResource {
    private Long id;
    private int amount;
    private Double price;
    private String scheduledDate;
    private String status;

    //Relationships
    private TouristResource tourist;
    private ServiceResource service;
}
