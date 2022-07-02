package com.dh.beTFI.dentalPractices.service.dentist;

import com.dh.beTFI.dentalPractices.model.Dentist;
import com.dh.beTFI.dentalPractices.repository.IDentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistService implements IDentistService {
    @Autowired
    private IDentistRepository dentistRepository;

    @Override
    public List<Dentist> getAll() {
        return dentistRepository.findAll();
    }

    @Override
    public Optional<Dentist> getById(Long id) {
        return dentistRepository.findById(id);
    }

    @Override
    public Dentist save(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    /* @Override
    public Dentist update(Dentist dentist) {
        return null;
    } */

    @Override
    public void delete(Long id) {
        dentistRepository.deleteById(id);
    }
}
