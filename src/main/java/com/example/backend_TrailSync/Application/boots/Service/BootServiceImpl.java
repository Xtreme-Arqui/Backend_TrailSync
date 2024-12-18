package com.example.backend_TrailSync.Application.boots.Service;

import com.example.backend_TrailSync.Application.Agency.Domain.Model.Agency;
import com.example.backend_TrailSync.Application.Agency.Domain.Persistence.AgencyRepository;
import com.example.backend_TrailSync.Application.boots.Domain.Model.Boot;
import com.example.backend_TrailSync.Application.boots.Domain.Persistence.BootRepository;
import com.example.backend_TrailSync.Application.boots.Domain.Service.BootService;
import com.example.backend_TrailSync.Application.services.domain.model.entity.Service;
import com.example.backend_TrailSync.Application.services.domain.persistence.ServiceRepository;
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
public class BootServiceImpl implements BootService {

    private static final String ENTITY = "BootService";

    private final BootRepository bootRepository;

    private final ServiceRepository serviceRepository;
    private final AgencyRepository agencyRepository;

    private final TouristRepository touristRepository;

    private final Validator validator;

    public BootServiceImpl(BootRepository bootRepository, ServiceRepository serviceRepository, TouristRepository touristRepository, Validator validator,AgencyRepository agencyRepository) {
        this.bootRepository = bootRepository;
        this.serviceRepository = serviceRepository;
        this.touristRepository = touristRepository;
        this.agencyRepository=agencyRepository;
        this.validator = validator;
    }

    @Override
    public List<Boot> getAll() {
        return bootRepository.findAll();
    }

    @Override
    public Boot getById(Long bootId) {
        return bootRepository.findById(bootId).orElseThrow(() ->
                new ResourceNotFoundException(ENTITY, bootId));
    }

    @Override
    public Boot getByCode(Long code) {
        return bootRepository.findByCode(code);
    }

    @Override
    public Boot getByState(String state) {
        return bootRepository.findByState(state);
    }

    @Override
    public Boot getByLongitudeAndLatitude(Double longitude, Double latitude) {
        return bootRepository.findByLongitudeAndLatitude(longitude,latitude);
    }

    @Override
    public Boot getByDistance(Double distance) {
        return bootRepository.findByDistance(distance);
    }

    @Override
    public Boot create(Long serviceId, Long touristId, Long agencyId, Boot boot) {
        // Validar los datos de Boot
        Set<ConstraintViolation<Boot>> violations = validator.validate(boot);
        if (!violations.isEmpty())
            throw new ResourceValidationException("Boot", (Throwable) violations);

        // Verificar que el servicio, la agencia y el turista existan
        if (!serviceRepository.existsById(serviceId))
            throw new ResourceNotFoundException("Service", serviceId);

        if (!agencyRepository.existsById(agencyId))
            throw new ResourceNotFoundException("Agency", agencyId);

        if (!touristRepository.existsById(touristId))
            throw new ResourceNotFoundException("Tourist", touristId);

        // Obtener entidades existentes
        Service service = serviceRepository.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("Service", serviceId));
        Agency agency = agencyRepository.findById(agencyId).orElseThrow(() -> new ResourceNotFoundException("Agency", agencyId));
        return touristRepository.findById(touristId).map(tourist -> {
            boot.setService(service);
            boot.setTourist(tourist);
            boot.setAgency(agency);
            return bootRepository.save(boot);
        }).orElseThrow(() -> new ResourceNotFoundException("Tourist", touristId));
    }


    @Override
    public Boot update(Long serviceId, Long touristId, Long bootId, Boot boot) {
        // Validar los datos de Boot
        Set<ConstraintViolation<Boot>> violations = validator.validate(boot);
        if (!violations.isEmpty())
            throw new ResourceValidationException("Boot", (Throwable) violations);

        // Verificar que el servicio y el turista existen
        if (!serviceRepository.existsById(serviceId))
            throw new ResourceNotFoundException("Service", serviceId);
        if (!touristRepository.existsById(touristId))
            throw new ResourceNotFoundException("Tourist", touristId);

        // Actualizar los datos de Boot si existe
        return bootRepository.findById(bootId).map(existingBoot ->
                bootRepository.save(existingBoot
                        .withCode(boot.getCode())
                        .withState(boot.getState())
                        .withBatery(boot.getBatery())
                        .withSteps(boot.getSteps())
                        .withLatitude(boot.getLatitude())
                        .withLongitude(boot.getLongitude())
                        .withDistance(boot.getDistance())
                        .withHeartRate(boot.getHeartRate())
                        .withTemperature(boot.getTemperature())
                )
        ).orElseThrow(() -> new ResourceNotFoundException("Boot", bootId));
    }

    @Override
    public ResponseEntity<?> delete(Long serviceId, Long touristId, Long bootId) {
        // Buscar y eliminar el Boot si existe
        return bootRepository.findByIdAndServiceIdAndTouristId(bootId, serviceId, touristId).map(boot -> {
            bootRepository.delete(boot);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Boot", bootId));
    }

}
