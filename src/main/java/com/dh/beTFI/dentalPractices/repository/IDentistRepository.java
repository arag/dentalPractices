package com.dh.beTFI.dentalPractices.repository;

import com.dh.beTFI.dentalPractices.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDentistRepository extends JpaRepository<Dentist, Long> {
    Optional<Dentist> findByProfessionalLicenseNumber(Integer professionalLicenseNumber);
}
