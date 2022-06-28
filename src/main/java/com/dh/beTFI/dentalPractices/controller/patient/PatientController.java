package com.dh.beTFI.dentalPractices.controller.patient;

import com.dh.beTFI.dentalPractices.model.Patient;
import com.dh.beTFI.dentalPractices.service.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /*
    @GetMapping("/patient")
    public String traerPacienteXEmail(Model model, @RequestParam("email") String email){
        Paciente paciente=pacienteService.buscarXEmail(email);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        return "paciente";
    }
    */

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") int id) {
        Patient response = patientService.getById(id);

        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patient> savePatient(@RequestBody Patient newPatient){
        return new ResponseEntity<>(patientService.save(newPatient), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient updatePatient) {
        Patient patientFound = patientService.getById(updatePatient.getId());

        if (patientFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(patientService.update(updatePatient), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable("id") int id) {
        Patient patientFound = patientService.getById(id);

        if (patientFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        patientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
