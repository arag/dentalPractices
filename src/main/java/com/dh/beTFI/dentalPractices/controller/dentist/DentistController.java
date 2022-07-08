package com.dh.beTFI.dentalPractices.controller.dentist;

import com.dh.beTFI.dentalPractices.exception.BadRequestException;
import com.dh.beTFI.dentalPractices.exception.ResourceNotFoundException;
import com.dh.beTFI.dentalPractices.model.Dentist;
import com.dh.beTFI.dentalPractices.service.dentist.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentists")
public class DentistController {
    @Autowired
    private IDentistService dentistService;

    @GetMapping
    public ResponseEntity<List<Dentist>> getAllDentist() {
        return ResponseEntity.ok(dentistService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> getDentistById(@PathVariable("id") Long id) throws ResourceNotFoundException, BadRequestException {
        return ResponseEntity.ok(dentistService.getById(id).get());
    }

    @PostMapping
    public ResponseEntity<Dentist> createDentist(@RequestBody Dentist newDentist) throws BadRequestException {
        return new ResponseEntity<>(dentistService.create(newDentist), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Dentist> updateDentist(@RequestBody Dentist updateDentist) throws ResourceNotFoundException, BadRequestException {
        return ResponseEntity.ok(dentistService.update(updateDentist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeDentist(@PathVariable("id") Long id) throws ResourceNotFoundException, BadRequestException {
        dentistService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
