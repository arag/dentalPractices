package com.dh.beTFI.dentalPractices.service.dentist;

import com.dh.beTFI.dentalPractices.model.Dentist;

import java.util.List;

public interface IDentistService {
    List<Dentist> getAll();
    Dentist getById(int id);
    Dentist save(Dentist dentist);
    Dentist update(Dentist dentist);
    void delete(int id);
}
