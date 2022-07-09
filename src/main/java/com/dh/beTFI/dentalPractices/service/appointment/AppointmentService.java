package com.dh.beTFI.dentalPractices.service.appointment;

import com.dh.beTFI.dentalPractices.exception.BadRequestException;
import com.dh.beTFI.dentalPractices.exception.ResourceNotFoundException;
import com.dh.beTFI.dentalPractices.model.Dentist;
import com.dh.beTFI.dentalPractices.model.Patient;
import com.dh.beTFI.dentalPractices.service.dentist.IDentistService;
import com.dh.beTFI.dentalPractices.service.patient.IPatientService;
import com.dh.beTFI.dentalPractices.model.Appointment;
import com.dh.beTFI.dentalPractices.service.dentist.DentistService;
import com.dh.beTFI.dentalPractices.repository.IAppointmentRepository;
import com.dh.beTFI.dentalPractices.util.validators.ValidateResources;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> getById(Long id) throws BadRequestException, ResourceNotFoundException {
        logger.info("\n========== Looking for appointment by ID " + id);

        if (ValidateResources.invalidId(id)) {
            logger.error("\n========== Invalid id: " + id);
            throw new BadRequestException("Id is required");
        }

        Optional<Appointment> appointmentFound = appointmentRepository.findById(id);

        if (appointmentFound.isEmpty()) {
            logger.error(String.format("\n========== Appointment with ID %s not found", id));
            throw new ResourceNotFoundException("Appointment id " + id + " not found");
        }

        return appointmentFound;
    }

    @Override
    public Appointment create(Appointment appointment) throws BadRequestException, ResourceNotFoundException {
        if (ValidateResources.invalidAppointmentData(appointment)) {
            logger.error(String.format("\n========== ERROR SAVING NEW APPOINTMENT. APPOINTMENT DATA: %s", appointment.showAppointmentData()));
            throw new BadRequestException("Invalid Appointment data. Appointment: " + appointment.showAppointmentData());
        }

        Optional<Dentist> dentistFound = dentistService.getById(appointment.getDentist().getId());

        if (dentistFound.isEmpty()) {
            logger.error(String.format("\n========== ERROR SAVING NEW APPOINTMENT. INVALID DENTIST ID: %s", appointment.getDentist().getId()));
            throw new ResourceNotFoundException("Dentist does not exist");
        }

        Optional<Patient> patientFound = patientService.getById(appointment.getPatient().getId());

        if (patientFound.isEmpty()) {
            logger.error(String.format("\n========== ERROR SAVING NEW APPOINTMENT. INVALID PATIENT ID: %s", appointment.getPatient().getId()));
            throw new ResourceNotFoundException("Patient does not exist. Please register patient");
        }

        String loggerMessage = String.format("\n========== SAVING NEW APPOINTMENT - APPOINTMENT DATA: = %s",
                appointment.showAppointmentData());

        logger.info(loggerMessage);

        Appointment appointmentCreated = appointmentRepository.save(appointment);
        appointmentCreated.setDentist(dentistFound.get());
        appointmentCreated.setPatient(patientFound.get());

        return appointmentCreated;
    }

    @Override
    public Appointment update(Appointment appointment) throws BadRequestException, ResourceNotFoundException {
        Optional<Appointment> appointmentFound = getById(appointment.getId());

        // Si el getById me responde con appointment es porque existe si no debería haber saltado la excepción
        if (ValidateResources.invalidAppointmentData(appointment)) {
            logger.error(String.format("\n========== ERROR SAVING NEW APPOINTMENT. APPOINTMENT DATA: %s", appointment.showAppointmentData()));
            throw new BadRequestException("Invalid Appointment data. Appointment: " + appointment.showAppointmentData());
        }

        Optional<Dentist> dentistFound = dentistService.getById(appointment.getDentist().getId());

        if (dentistFound.isEmpty()) {
            logger.error(String.format("\n========== ERROR SAVING NEW APPOINTMENT. INVALID DENTIST ID: %s", appointment.getDentist().getId()));
            throw new ResourceNotFoundException("Dentist does not exist");
        }

        Optional<Patient> patientFound = patientService.getById(appointment.getPatient().getId());

        if (patientFound.isEmpty()) {
            logger.error(String.format("\n========== ERROR SAVING NEW APPOINTMENT. INVALID PATIENT ID: %s", appointment.getPatient().getId()));
            throw new ResourceNotFoundException("Patient does not exist. Please register patient");
        }

        String loggerMessage = String.format("\n========== UPDATING APPOINTMENT - OLD APPOINTMENT DATA: = %s", appointmentFound);

        logger.info(loggerMessage);

        Appointment appointmentUpdated = appointmentRepository.save(appointment);
        appointmentUpdated.setDentist(dentistFound.get());
        appointmentUpdated.setPatient(patientFound.get());

        return appointmentUpdated;
    }

    @Override
    public void delete(Long id) throws BadRequestException, ResourceNotFoundException {
        Optional<Appointment> appointmentFound = getById(id);

        logger.info(String.format("\n========== REMOVING APPOINTMENT WITH ID %s", appointmentFound.get().getId()));

        appointmentRepository.deleteById(id);
    }
}
