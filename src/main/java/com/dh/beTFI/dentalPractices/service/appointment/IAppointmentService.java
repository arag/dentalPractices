package com.dh.beTFI.dentalPractices.service.appointment;

import com.dh.beTFI.dentalPractices.exception.BadRequestException;
import com.dh.beTFI.dentalPractices.exception.ResourceNotFoundException;
import com.dh.beTFI.dentalPractices.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface IAppointmentService {
    List<Appointment> getAll();

    Optional<Appointment> getById(Long id) throws BadRequestException, ResourceNotFoundException;

    Appointment create(Appointment appointment) throws BadRequestException, ResourceNotFoundException;

    Appointment update(Appointment appointment) throws BadRequestException, ResourceNotFoundException;

    void delete(Long id) throws BadRequestException, ResourceNotFoundException;
}
