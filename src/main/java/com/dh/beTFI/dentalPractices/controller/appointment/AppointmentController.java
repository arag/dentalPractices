package com.dh.beTFI.dentalPractices.controller.appointment;

import com.dh.beTFI.dentalPractices.dto.AppointmentDTO;
import com.dh.beTFI.dentalPractices.exception.BadRequestException;
import com.dh.beTFI.dentalPractices.exception.ResourceNotFoundException;
import com.dh.beTFI.dentalPractices.model.Appointment;
import com.dh.beTFI.dentalPractices.model.Dentist;
import com.dh.beTFI.dentalPractices.model.Patient;
import com.dh.beTFI.dentalPractices.service.appointment.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private IAppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Appointment> createDentist(@RequestBody AppointmentDTO newAppointmentDTO) throws BadRequestException, ResourceNotFoundException {
        Dentist dentistRequest = new Dentist(newAppointmentDTO.getDentistId());
        Patient patientRequest = new Patient(newAppointmentDTO.getPatientId());
        Appointment appointmentRequestData = new Appointment(dentistRequest, patientRequest, newAppointmentDTO.getAppointmentDate());
        return new ResponseEntity<>(appointmentService.save(appointmentRequestData), HttpStatus.CREATED);
    }
}
