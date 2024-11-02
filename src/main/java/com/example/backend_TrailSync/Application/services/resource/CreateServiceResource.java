package com.example.backend_TrailSync.Application.services.resource;

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
public class CreateServiceResource {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    @Size(max = 500)

    private String description;
    @NotNull
    @NotBlank
    private String location;
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

}
