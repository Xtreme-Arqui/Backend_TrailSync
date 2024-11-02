package com.example.backend_TrailSync.Application.serviceReviews.service;


import com.example.backend_TrailSync.Application.Agency.Domain.Model.Agency;
import com.example.backend_TrailSync.Application.Agency.Domain.Persistence.AgencyRepository;
import com.example.backend_TrailSync.Application.serviceReviews.domain.model.entity.ServiceReview;
import com.example.backend_TrailSync.Application.serviceReviews.domain.persistence.ServiceReviewRepository;
import com.example.backend_TrailSync.Application.serviceReviews.domain.service.ServiceReviewService;
import com.example.backend_TrailSync.Application.tourists.domain.persistence.TouristRepository;
import com.example.backend_TrailSync.Mapping.Exception.ResourceNotFoundException;
import com.example.backend_TrailSync.Mapping.Exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@org.springframework.stereotype.Service
public class ServiceReviewServiceImpl implements ServiceReviewService {

    private static final String ENTITY = "ServiceReview";

    private final ServiceReviewRepository serviceReviewRepository;
    private final AgencyRepository agencyRepository;

    private final TouristRepository touristRepository;

    private final Validator validator;

    public ServiceReviewServiceImpl(ServiceReviewRepository serviceReviewRepository, AgencyRepository agencyRepository, TouristRepository touristRepository, Validator validator) {
        this.serviceReviewRepository = serviceReviewRepository;
        this.agencyRepository = agencyRepository;
        this.touristRepository = touristRepository;
        this.validator = validator;
    }

    @Override
    public List<ServiceReview> getAll() {
        return serviceReviewRepository.findAll();
    }

    @Override
    public ServiceReview getById(Long serviceReviewId) {
        return serviceReviewRepository.findById(serviceReviewId).orElseThrow(() ->
                new ResourceNotFoundException(ENTITY, serviceReviewId));
    }


    @Override
    public List<ServiceReview> getAllByAgencyId(Long agencyId) {
        return serviceReviewRepository.findByAgencyId(agencyId);
    }

    @Override
    public List<ServiceReview> getAllByTouristId(Long touristId) {
        return serviceReviewRepository.findByTouristId(touristId);
    }

    @Override
    public ServiceReview create(Long agencyId, Long touristId, ServiceReview serviceReview) {
        Set<ConstraintViolation<ServiceReview>> violations = validator.validate(serviceReview);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, (Throwable) violations);

        if(!agencyRepository.existsById(agencyId))
            throw new ResourceNotFoundException("Agency", agencyId);

        Optional<Agency> agencyExisting =  agencyRepository.findById(agencyId);

        return touristRepository.findById(touristId).map(tourist -> {
            serviceReview.setAgency(agencyExisting.get());
            serviceReview.setTourist(tourist);
            return serviceReviewRepository.save(serviceReview);
        }).orElseThrow(() -> new ResourceNotFoundException("Tourist", touristId));
    }

    @Override
    public ServiceReview update(Long agencyId, Long touristId, Long serviceReviewId, ServiceReview serviceReview) {
        Set<ConstraintViolation<ServiceReview>> violations = validator.validate(serviceReview);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, (Throwable) violations);

        if(!agencyRepository.existsById(agencyId))
            throw new ResourceNotFoundException("Agency", agencyId);

        if(!touristRepository.existsById(touristId))
            throw new ResourceNotFoundException("Tourist", touristId);

        return serviceReviewRepository.findById(serviceReviewId).map(existingServiceReview ->
                        serviceReviewRepository.save(existingServiceReview.withComment(serviceReview.getComment())
                                .withScore(serviceReview.getScore()).withDate(serviceReview.getDate())))
                .orElseThrow(() -> new ResourceNotFoundException("ServiceReview", serviceReviewId));
    }

    @Override
    public ResponseEntity<?> delete(Long agencyId, Long touristId, Long serviceReviewId) {
        return serviceReviewRepository.findByIdAndAgencyIdAndTouristId(serviceReviewId, agencyId, touristId).map(serviceReview -> {
            serviceReviewRepository.delete(serviceReview);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, serviceReviewId));
    }
}
