package com.example.backend_TrailSync.Application.boots.Domain.Model;

import com.example.backend_TrailSync.Application.Agency.Domain.Model.Agency;
import com.example.backend_TrailSync.Application.services.domain.model.entity.Service;
import com.example.backend_TrailSync.Application.tourists.domain.model.entity.Tourist;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "boots")
public class Boot {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tourist_id", nullable = false)
    private Tourist tourist;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agency_id", nullable = false)
    private Agency agency;
}
