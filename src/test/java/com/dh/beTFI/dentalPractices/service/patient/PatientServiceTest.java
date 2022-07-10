package com.dh.beTFI.dentalPractices.service.patient;

import com.dh.beTFI.dentalPractices.exception.BadRequestException;
import com.dh.beTFI.dentalPractices.exception.ResourceNotFoundException;
import com.dh.beTFI.dentalPractices.model.Address;
import com.dh.beTFI.dentalPractices.model.Patient;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PatientServiceTest {
    @Autowired
    IPatientService patientService;

    @Test
    @Order(1)
    public void saveNewPatientTest() throws BadRequestException {
        Address addressTest = new Address("Av. Siempre Viva", 742, "Springfield", "Springfield");
        Patient patientTest = new Patient("Simpson", "Lisa", "ls@simpson.com",
                5555, LocalDate.parse("2022-05-05"), addressTest);
        Patient created = patientService.create(patientTest);
        assertNotNull(created);
    }

    @Test
    @Order(2)
    public void getPatientById() throws BadRequestException, ResourceNotFoundException {
        Long idTest = 1L;
        Optional<Patient> PatientFound = patientService.getById(idTest);
        assertTrue(PatientFound.isPresent());
    }

    @Test
    @Order(3)
    public void getAllPatients() {
        List<Patient> patientListTest = patientService.getAll();
        assertTrue(patientListTest.size() > 0);
    }

    @Test
    @Order(4)
    public void updatePatient() throws BadRequestException, ResourceNotFoundException {
        Address addressTest = new Address(1L, "Av. Siempre Viva", 742, "Springfield", "Springfield");
        Patient patientTest = new Patient(1L,"Simpson", "Lisa", "lisa@simpson.com",
                5555, LocalDate.parse("2022-05-05"), addressTest);
        Patient updated = patientService.update(patientTest);
        assertEquals(updated.getEmail(), patientTest.getEmail());
    }

    @Test
    @Order(5)
    public void deletePatient() throws BadRequestException, ResourceNotFoundException {
        Long idTest = 1L;

        patientService.delete(idTest);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> patientService.getById(idTest));

        String expectedMessage = "Patient id " + idTest + " not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}