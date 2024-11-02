package com.example.backend_TrailSync.Application.serviceReviews.domain.persistence;

import com.example.backend_TrailSync.Application.serviceReviews.domain.model.entity.ServiceReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceReviewRepository extends JpaRepository<ServiceReview, Long> {
    List<ServiceReview> findByAgencyId(Long agencyId);


    Optional<ServiceReview> findByIdAndAgencyId(Long id, Long agencyId);
    Page<ServiceReview> findByAgencyId(Long agencyId, Pageable pageable);

    List<ServiceReview> findByTouristId(Long touristId);
    Optional<ServiceReview> findByIdAndTouristId(Long id, Long touristId);

    Optional<ServiceReview> findByIdAndAgencyIdAndTouristId(Long id, Long agencyId, Long touristId);
}
