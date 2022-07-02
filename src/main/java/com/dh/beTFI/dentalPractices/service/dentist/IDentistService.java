package com.dh.beTFI.dentalPractices.service.dentist;

import com.dh.beTFI.dentalPractices.model.Dentist;

import java.util.List;
import java.util.Optional;

public interface IDentistService {
    List<Dentist> getAll();

    Optional<Dentist> getById(Long id);

    Dentist save(Dentist dentist);

    /*Dentist update(Dentist dentist);*/

    void delete(Long id);
}
