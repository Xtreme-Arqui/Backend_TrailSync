package com.example.backend_TrailSync.Application.boots.Domain.Service;

import com.example.backend_TrailSync.Application.boots.Domain.Model.Boot;
import com.example.backend_TrailSync.Application.hiredServices.domain.model.entity.HiredService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BootService {
    List<Boot> getAll();
    Boot getById(Long id);
    Boot getByCode(Long code);
    Boot getByState(String state);
    Boot getByLongitudeAndLatitude(Double longitude, Double latitude);
    Boot getByDistance(Double distance);
    Boot create(Long serviceId, Long touristId, Boot BootService);
    Boot update(Long serviceId, Long touristId, Long hiredServiceId, Boot BootService);
    ResponseEntity<?> delete(Long serviceId, Long touristId, Long bootId);

}
