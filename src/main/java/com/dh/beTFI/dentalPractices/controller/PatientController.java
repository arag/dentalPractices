package com.dh.beTFI.dentalPractices.controller;

import com.dh.beTFI.dentalPractices.exception.BadRequestException;
import com.dh.beTFI.dentalPractices.exception.ResourceNotFoundException;
import com.dh.beTFI.dentalPractices.model.Patient;
import com.dh.beTFI.dentalPractices.service.patient.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private IPatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id) throws ResourceNotFoundException, BadRequestException {
        Optional<Patient> patientFound = patientService.getById(id);
        return ResponseEntity.ok(patientFound.get());
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient newPatient) throws BadRequestException {
        newPatient.setAdmissionDate(LocalDate.now());
        return new ResponseEntity<>(patientService.create(newPatient), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient updatePatient) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(patientService.update(updatePatient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePatient(@PathVariable("id") Long id) throws BadRequestException, ResourceNotFoundException {
        patientService.delete(id);
        return ResponseEntity.ok("Patient successfully removed");
    }
}
