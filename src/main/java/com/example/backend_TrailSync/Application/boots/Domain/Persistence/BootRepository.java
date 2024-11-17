package com.example.backend_TrailSync.Application.boots.Domain.Persistence;


import com.example.backend_TrailSync.Application.boots.Domain.Model.Boot;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BootRepository extends JpaRepository<Boot, Long> {
    Boot findByCode(Long code);
    Boot findByState(String state);
    Boot findByLongitudeAndLatitude(Double longitude, Double latitude);
    Boot findByDistance(Double distance);
    List<Boot> findAll();
    List<Boot> findAllByState(String state);

    Optional<Boot> findByIdAndServiceIdAndTouristId(Long bootId, Long serviceId, Long touristId);
}
