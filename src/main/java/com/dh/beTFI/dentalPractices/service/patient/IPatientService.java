package com.dh.beTFI.dentalPractices.service.patient;

import com.dh.beTFI.dentalPractices.model.Patient;

import java.util.List;

public interface IPatientService {
    List<Patient> getAll();
    Patient getById(int id);

    Patient save(Patient patient);

    Patient update(Patient patient);

    void delete(int id);
}
