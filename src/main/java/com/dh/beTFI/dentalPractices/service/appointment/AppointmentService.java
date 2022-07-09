package com.dh.beTFI.dentalPractices.exception.service.appointment;

import com.dh.beTFI.dentalPractices.exception.BadRequestException;
import com.dh.beTFI.dentalPractices.exception.ResourceNotFoundException;
import com.dh.beTFI.dentalPractices.exception.service.dentist.IDentistService;
import com.dh.beTFI.dentalPractices.exception.service.patient.IPatientService;
import com.dh.beTFI.dentalPractices.model.Appointment;
import com.dh.beTFI.dentalPractices.exception.service.dentist.DentistService;
import com.dh.beTFI.dentalPractices.repository.IAppointmentRepository;
import com.dh.beTFI.dentalPractices.util.validators.ValidateResources;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService implements IAppointmentService {
    private static final Logger logger = Logger.getLogger(DentistService.class);

    @Autowired
    private final IAppointmentRepository appointmentRepository;

    @Autowired
    private final IDentistService dentistService;

    @Autowired
    private final IPatientService patientService;

    public AppointmentService(IAppointmentRepository appointmentRepository, IDentistService dentistService, IPatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }

    @Override
    public Appointment save(Appointment appointment) throws BadRequestException, ResourceNotFoundException {
        String loggerMessage = String.format("\n========== Saving new appointment - Appointment Data: = %s",
                appointment);

        logger.info(loggerMessage);

        if (ValidateResources.invalidAppointmentData(appointment)) {
            logger.error(String.format("\n========== Error saving new appointment. Appointment data: %s", appointment));
            throw new BadRequestException("INVALID APPOINTMENT DATA");
        }

        if (dentistService.getById(appointment.getDentist().getId()).isEmpty()) {
            logger.error(String.format("\n========== Error saving new appointment. Invalid Dentist Id: %s", appointment.getDentist().getId()));
            throw new ResourceNotFoundException("DENTIST DOES NOT EXISTS");
        }

        if (patientService.getById(appointment.getPatient().getId()).isEmpty()) {
            logger.error(String.format("\n========== Error saving new appointment. Invalid Patient Id: %s", appointment.getPatient().getId()));
            throw new ResourceNotFoundException("PATIENT DOES NOT EXISTS. PLEASE, REGISTER PATIENT BEFORE");
        }

        return appointmentRepository.save(appointment);
    }
}
