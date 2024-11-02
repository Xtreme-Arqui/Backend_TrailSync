package com.example.backend_TrailSync.Application.hiredServices.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PatchHiredServiceResource {
    private int amount;
    private Double price;
    private String scheduledDate;
    private String status;
}
