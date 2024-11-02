package com.example.backend_TrailSync.Application.serviceReviews.resource;

import com.example.backend_TrailSync.Application.services.resource.ServiceResource;
import com.example.backend_TrailSync.Application.tourists.resource.TouristResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ServiceReviewResource {
    private Long id;
    private String date;
    private String comment;
    private float score;
    private ServiceResource service;
    private TouristResource tourist;
}
