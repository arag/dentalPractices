package com.dh.beTFI.dentalPractices.service.appointment;

import com.dh.beTFI.dentalPractices.model.Appointment;
import com.dh.beTFI.dentalPractices.model.Dentist;

public interface IAppointmentService {
    Appointment save(Dentist dentist);
}
