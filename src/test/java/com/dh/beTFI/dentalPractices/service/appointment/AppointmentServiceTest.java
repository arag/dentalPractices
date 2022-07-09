package com.dh.beTFI.dentalPractices.service.appointment;

import com.dh.beTFI.dentalPractices.exception.BadRequestException;
import com.dh.beTFI.dentalPractices.exception.ResourceNotFoundException;
import com.dh.beTFI.dentalPractices.model.Address;
import com.dh.beTFI.dentalPractices.model.Appointment;
import com.dh.beTFI.dentalPractices.model.Dentist;
import com.dh.beTFI.dentalPractices.model.Patient;
import com.dh.beTFI.dentalPractices.service.dentist.IDentistService;
import com.dh.beTFI.dentalPractices.service.patient.IPatientService;
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
class AppointmentServiceTest {
    @Autowired
    IDentistService dentistService;

    @Autowired
    IPatientService patientService;

    @Autowired
    IAppointmentService appointmentService;

    @Test
    @Order(1)
    public void saveNewAppointmentTest() throws BadRequestException, ResourceNotFoundException {
        Dentist dentistTest = new Dentist("Messi", "Lionel", 1234);
        Dentist dentistCreated = dentistService.create(dentistTest);

        Address addressTest = new Address("Av. Siempre Viva", 742, "Springfield", "Springfield");
        Patient patientTest = new Patient("Simpson", "Lisa", "ls@simpson.com",
                5555, LocalDate.parse("2022-05-05"), addressTest);
        Patient patientCreated = patientService.create(patientTest);

        LocalDate date = LocalDate.now();

        Appointment appointmentTest = new Appointment(dentistCreated, patientCreated, date);
        Appointment appointmentCreated = appointmentService.create(appointmentTest);

        assertNotNull(appointmentCreated);
    }

    @Test
    @Order(2)
    public void getAppointmentById() throws BadRequestException, ResourceNotFoundException {
        Long idTest = 1L;
        Optional<Appointment> appointmentFound = appointmentService.getById(idTest);
        assertTrue(appointmentFound.isPresent());
    }

    @Test
    @Order(3)
    public void getAllAppointment() {
        List<Appointment> appointmentListTest = appointmentService.getAll();
        assertTrue(appointmentListTest.size() > 0);
    }

}