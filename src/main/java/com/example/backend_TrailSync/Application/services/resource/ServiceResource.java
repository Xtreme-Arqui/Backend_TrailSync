package com.example.backend_TrailSync.Application.services.resource;


import com.example.backend_TrailSync.Application.Agency.Domain.Model.Agency;
import com.example.backend_TrailSync.Application.Agency.Resource.AgencyResource;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResource {
    private Long id;
    private String name;
    private String description;
    private String location;
    private int score;
    private float price;
    private float distance;
    private String difficult;
    private float altMax;
    private float altMin;
    private String typeRoute;
    private List<String> photos;
    private AgencyResource agency;
}
