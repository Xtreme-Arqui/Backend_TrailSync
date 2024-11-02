package com.example.backend_TrailSync.Application.Agency.Resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateAgencyResource {
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String password;
    @NotNull
    private Long phoneNumber;
    @NotBlank
    @NotNull
    @Size(max = 500)
    private String description;
    @NotBlank
    @NotNull
    private String location;
    @NotNull
    private Long ruc;
    @NotBlank
    @NotNull
    private String photo;
    @NotNull
    private int score;

    @NotBlank
    @NotNull
    private String linkF;

    @NotBlank
    @NotNull
    private String linkW;

    @NotBlank
    @NotNull
    private String linkI;

    @NotBlank
    @NotNull
    private String linkT;
}
