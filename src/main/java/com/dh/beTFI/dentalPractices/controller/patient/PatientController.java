package com.dh.beTFI.dentalPractices.controller.patient;

import com.dh.beTFI.dentalPractices.model.Patient;
import com.dh.beTFI.dentalPractices.service.patient.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id) {
        Optional<Patient> response = patientService.getById(id);

        if (response.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(response.get());
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient newPatient){
        return new ResponseEntity<>(patientService.save(newPatient), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient updatePatient) {
        Optional<Patient> patientFound = patientService.getById(updatePatient.getId());

        if (patientFound.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(patientService.save(updatePatient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removePatient(@PathVariable("id") Long id) {
        Optional<Patient> patientFound = patientService.getById(id);

        if (patientFound.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        patientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
