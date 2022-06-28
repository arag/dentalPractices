package com.dh.beTFI.dentalPractices.service.patient;

import com.dh.beTFI.dentalPractices.model.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    List<Patient> getAll();

    Optional<Patient> getById(Long id);

    Patient save(Patient patient);

    /*Patient update(Patient patient);*/

    void delete(Long id);
}
