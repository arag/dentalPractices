package com.dh.beTFI.dentalPractices.exception.service.appointment;

import com.dh.beTFI.dentalPractices.exception.BadRequestException;
import com.dh.beTFI.dentalPractices.exception.ResourceNotFoundException;
import com.dh.beTFI.dentalPractices.model.Appointment;

public interface IAppointmentService {
    Appointment save(Appointment appointment) throws BadRequestException, ResourceNotFoundException;
}
