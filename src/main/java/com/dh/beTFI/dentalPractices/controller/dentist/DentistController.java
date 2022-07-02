package com.dh.beTFI.dentalPractices.controller.dentist;

import com.dh.beTFI.dentalPractices.model.Dentist;
import com.dh.beTFI.dentalPractices.service.dentist.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Dentist> getDentistById(@PathVariable("id") Long id) {
        Optional<Dentist> response = dentistService.getById(id);

        if (response.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(response.get());
    }

    @PostMapping
    public ResponseEntity<Dentist> createDentist(@RequestBody Dentist newDentist){
        return new ResponseEntity<>(dentistService.save(newDentist), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Dentist> updateDentist(@RequestBody Dentist updateDentist) {
        Optional<Dentist> dentistFound = dentistService.getById(updateDentist.getId());

        if (dentistFound.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(dentistService.save(updateDentist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeDentist(@PathVariable("id") Long id) {
        Optional<Dentist> dentistFound = dentistService.getById(id);

        if (dentistFound.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        dentistService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
