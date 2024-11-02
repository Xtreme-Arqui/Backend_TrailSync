package com.example.backend_TrailSync.Application.Agency.Domain.Persistence;

import com.example.backend_TrailSync.Application.Agency.Domain.Model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
    Agency findByEmail(String email);
    Agency findByPhoneNumber(Long phoneNumber);
    Agency findByName(String name);

}
