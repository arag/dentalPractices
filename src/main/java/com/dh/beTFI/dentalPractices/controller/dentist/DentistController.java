package com.dh.beTFI.dentalPractices.controller.dentist;

import com.dh.beTFI.dentalPractices.dto.DentistDTO;
import com.dh.beTFI.dentalPractices.model.Dentist;
import com.dh.beTFI.dentalPractices.service.dentist.IDentistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dentists")
public class DentistController {
    private final ObjectMapper mapper;
    private final IDentistService dentistService;

    @Autowired
    public DentistController(IDentistService dentistService, ObjectMapper mapper) {
        this.dentistService = dentistService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<DentistDTO>> getAllDentist() {
        List<Dentist> allDentists = dentistService.getAll();

        List<DentistDTO> responseDtoList = new ArrayList<>();

        if (allDentists.size() > 0) {
            for (Dentist dentist: allDentists) {
                DentistDTO dentistDto = mapper.convertValue(dentist, DentistDTO.class);
                responseDtoList.add(dentistDto);
            }
        }

        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistDTO> getDentistById(@PathVariable("id") int id) {
        Dentist response = dentistService.getById(id);

        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        DentistDTO responseDto = mapper.convertValue(response, DentistDTO.class);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DentistDTO> saveDentist(@RequestBody DentistDTO newDentistDto) {
        Dentist newDentist = mapper.convertValue(newDentistDto, Dentist.class);

        Dentist dentistCreated = dentistService.save(newDentist);

        DentistDTO responseDto = mapper.convertValue(dentistCreated, DentistDTO.class);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DentistDTO> updateDentist(@RequestBody DentistDTO updateDentistDto) {
        Dentist updateDentist = mapper.convertValue(updateDentistDto, Dentist.class);

        Dentist dentistFound = dentistService.getById(updateDentist.getId());

        if (dentistFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Dentist dentistUpdated = dentistService.save(updateDentist);

        DentistDTO responseDto = mapper.convertValue(dentistUpdated, DentistDTO.class);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDentist(@PathVariable("id") int id) {
        Dentist dentistFound = dentistService.getById(id);

        if (dentistFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        dentistService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
