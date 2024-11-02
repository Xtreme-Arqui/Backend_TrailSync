package com.example.backend_TrailSync.Application.Agency.Domain.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agencies")
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    @Column(unique = true)
    private String email;
    @NotBlank
    @NotNull
    private String password;
    @NotNull
    @Column(unique = true)
    private Long phoneNumber;
    @NotBlank
    @NotNull
    @Size(max = 5000)
    private String description;
    @NotBlank
    @NotNull
    private String location;
    @NotNull
    private Long ruc;
    @NotBlank
    @NotNull
    private String photo;

    private int score;

    @NotBlank
    private String linkF;

    @NotBlank
    private String linkW;

    @NotBlank
    private String linkI;

    @NotBlank
    private String linkT;
}
