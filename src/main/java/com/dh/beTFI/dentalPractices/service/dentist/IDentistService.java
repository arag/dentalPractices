package com.dh.beTFI.dentalPractices.service.dentist;

import com.dh.beTFI.dentalPractices.exception.BadRequestException;
import com.dh.beTFI.dentalPractices.exception.ResourceNotFoundException;
import com.dh.beTFI.dentalPractices.model.Dentist;

import java.util.List;
import java.util.Optional;

public interface IDentistService {
    List<Dentist> getAll();

    Optional<Dentist> getById(Long id) throws ResourceNotFoundException, BadRequestException;

    Dentist create(Dentist dentist) throws BadRequestException;

    Dentist update(Dentist dentist) throws ResourceNotFoundException, BadRequestException;

    void delete(Long id) throws ResourceNotFoundException, BadRequestException;
}
