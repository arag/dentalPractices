package com.dh.beTFI.dentalPractices.repository;

import com.dh.beTFI.dentalPractices.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByDni(Integer dni);
}
