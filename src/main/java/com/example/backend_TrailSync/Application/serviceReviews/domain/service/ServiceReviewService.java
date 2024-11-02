package com.example.backend_TrailSync.Application.serviceReviews.domain.service;

import com.example.backend_TrailSync.Application.serviceReviews.domain.model.entity.ServiceReview;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceReviewService {
    List<ServiceReview> getAll();
    ServiceReview getById(Long serviceReviewId);

    List<ServiceReview> getAllByAgencyId(Long agencyId);

    List<ServiceReview> getAllByTouristId(Long touristId);

    ServiceReview create(Long agencyId, Long touristId, ServiceReview serviceReview);
    ServiceReview update(Long agencyId, Long touristId, Long serviceReviewId, ServiceReview serviceReview);
    ResponseEntity<?> delete(Long agencyId, Long touristId, Long serviceReviewId);
}
