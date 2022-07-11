package com.dh.beTFI.dentalPractices.repository;

import com.dh.beTFI.dentalPractices.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByDni(Integer dni);
}
