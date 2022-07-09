package com.dh.beTFI.dentalPractices.exception.service.patient;

import com.dh.beTFI.dentalPractices.exception.BadRequestException;
import com.dh.beTFI.dentalPractices.exception.ResourceNotFoundException;
import com.dh.beTFI.dentalPractices.model.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    List<Patient> getAll();

    Optional<Patient> getById(Long id) throws BadRequestException, ResourceNotFoundException;

    Patient create(Patient patient) throws BadRequestException;

    Patient update(Patient patient) throws BadRequestException, ResourceNotFoundException;

    void delete(Long id) throws BadRequestException, ResourceNotFoundException;
}
