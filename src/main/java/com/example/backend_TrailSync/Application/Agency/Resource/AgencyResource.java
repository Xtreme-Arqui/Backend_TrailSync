package com.example.backend_TrailSync.Application.Agency.Resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgencyResource {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Long phoneNumber;
    private String description;
    private String location;
    private Long ruc;
    private String photo;
    private int score;
    private String linkF;
    private String linkW;
    private String linkI;
    private String linkT;
}
