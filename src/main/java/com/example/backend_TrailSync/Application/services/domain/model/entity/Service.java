package com.example.backend_TrailSync.Application.services.domain.model.entity;


import com.example.backend_TrailSync.Application.Agency.Domain.Model.Agency;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 500)

    private String description;

    @NotNull
    private int score;

    @NotNull
    private float price;

    @NotNull
    private float distance;

    @NotNull
    private String difficult;

    @NotNull
    private float altMax;

    @NotNull
    private float altMin;

    @NotNull
    @NotBlank
    private String typeRoute;
    @ElementCollection
    @CollectionTable(name = "service_photos", joinColumns = @JoinColumn(name = "service_id"))
    @Column(name = "photo")
    private List<String> photos;


    //Relationships
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agency_id", nullable = false)
    private Agency agency;

}
