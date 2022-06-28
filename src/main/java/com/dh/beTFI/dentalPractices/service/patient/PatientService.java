package com.dh.beTFI.dentalPractices.service.patient;

import com.dh.beTFI.dentalPractices.repository.IPatientRepository;
import com.dh.beTFI.dentalPractices.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private IPatientRepository patientRepository;

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> getById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    /*@Override
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }*/

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}
