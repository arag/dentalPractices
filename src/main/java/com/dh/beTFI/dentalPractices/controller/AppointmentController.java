package com.dh.beTFI.dentalPractices.controller;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private IAppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointment() {
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") Long id) throws BadRequestException, ResourceNotFoundException {
        Optional<Appointment> appointmentFound = appointmentService.getById(id);
        return ResponseEntity.ok(appointmentFound.get());
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDTO newAppointmentDTO) throws BadRequestException, ResourceNotFoundException {
        Dentist dentistRequest = new Dentist(newAppointmentDTO.getDentistId());
        Patient patientRequest = new Patient(newAppointmentDTO.getPatientId());
        Appointment appointmentRequestData = new Appointment(dentistRequest, patientRequest, newAppointmentDTO.getAppointmentDate());
        return new ResponseEntity<>(appointmentService.create(appointmentRequestData), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Appointment> updateAppointment(@RequestBody AppointmentDTO newAppointmentDTO) throws BadRequestException, ResourceNotFoundException {
        Dentist dentistRequest = new Dentist(newAppointmentDTO.getDentistId());
        Patient patientRequest = new Patient(newAppointmentDTO.getPatientId());
        Appointment appointmentRequestData = new Appointment(newAppointmentDTO.getId(), dentistRequest, patientRequest, newAppointmentDTO.getAppointmentDate());
        return ResponseEntity.ok(appointmentService.update(appointmentRequestData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAppointment(@PathVariable("id") Long id) throws ResourceNotFoundException, BadRequestException {
        appointmentService.delete(id);
        return new ResponseEntity<>("Appointment successfully removed", HttpStatus.NO_CONTENT);
    }
}
