package com.dh.beTFI.dentalPractices.service.dentist;

import com.dh.beTFI.dentalPractices.repository.dentistRepository.DentistRepositoryH2;
import com.dh.beTFI.dentalPractices.model.Dentist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistService implements IDentistService {

    private DentistRepositoryH2 dentistRepositoryH2;

    @Autowired
    public DentistService(DentistRepositoryH2 dentistRepositoryH2) {
        this.dentistRepositoryH2 = dentistRepositoryH2;
    }

    @Override
    public List<Dentist> getAll() {
        return dentistRepositoryH2.getAll();
    }

    @Override
    public Dentist getById(int id) {
        return dentistRepositoryH2.getById(id);
    }

    @Override
    public Dentist save(Dentist dentist) {
        return dentistRepositoryH2.save(dentist);
    }

    @Override
    public Dentist update(Dentist dentist) {
        return dentistRepositoryH2.update(dentist);
    }

    @Override
    public void delete(int id) {
        dentistRepositoryH2.delete(id);
    }
}
