package com.dh.beTFI.dentalPractices.service.dentist;

import com.dh.beTFI.dentalPractices.exception.BadRequestException;
import com.dh.beTFI.dentalPractices.exception.ResourceNotFoundException;
import com.dh.beTFI.dentalPractices.model.Dentist;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class DentistServiceTest {
    @Autowired
    IDentistService dentistService;

    @Test
    @Order(1)
    public void saveNewDentistTest() throws BadRequestException {
        Dentist dentistTest = new Dentist("Messi", "Lionel", 1234);
        Dentist created = dentistService.create(dentistTest);
        assertNotNull(created);
    }

    @Test
    @Order(2)
    public void getDentistById() throws BadRequestException, ResourceNotFoundException {
        Long idTest = 1L;
        Optional<Dentist> dentistFound = dentistService.getById(idTest);
        assertTrue(dentistFound.isPresent());
    }

    @Test
    @Order(3)
    public void getAllDentist() {
        List<Dentist> dentistListTest = dentistService.getAll();
        assertTrue(dentistListTest.size() > 0);
    }

    @Test
    @Order(4)
    public void updateDentist() throws BadRequestException, ResourceNotFoundException {
        Dentist dentistTest = new Dentist(1L, "Messi", "Lionel", 10);
        Dentist updated = dentistService.update(dentistTest);
        assertEquals(updated.getProfessionalLicenseNumber(), dentistTest.getProfessionalLicenseNumber());
    }

}