package com.dh.beTFI.dentalPractices.service.patient;

import com.dh.beTFI.dentalPractices.repository.patientRepository.PatientRepositoryH2;
import com.dh.beTFI.dentalPractices.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements IPatientService {

    private PatientRepositoryH2 patientRepositoryH2;

    @Autowired
    public PatientService(PatientRepositoryH2 patientRepositoryH2) {
        this.patientRepositoryH2 = patientRepositoryH2;
    }

    @Override
    public List<Patient> getAll() {
        return patientRepositoryH2.getAll();
    }

    @Override
    public Patient getById(int id) {
        return patientRepositoryH2.getById(id);
    }

    @Override
    public Patient save(Patient newPatient) {
        return patientRepositoryH2.save(newPatient);
    }

    @Override
    public Patient update(Patient patient) {
        return patientRepositoryH2.update(patient);
    }

    @Override
    public void delete(int id) {
        patientRepositoryH2.delete(id);
    }
}
