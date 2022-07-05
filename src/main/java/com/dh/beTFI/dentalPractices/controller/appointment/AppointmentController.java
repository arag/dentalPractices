package com.dh.beTFI.dentalPractices.controller.appointment;

import com.dh.beTFI.dentalPractices.exception.BadRequestException;
import com.dh.beTFI.dentalPractices.model.Appointment;
import com.dh.beTFI.dentalPractices.service.appointment.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    // minima data postman
    /*
    {
        "paciente": {
        "id": 1
    },
        "odontologo": {
        "id": 1
    },
        "fecha": "2022-06-16"
    }*/

    @Autowired
    private IAppointmentService appointmentService;

    /*
    @PostMapping
    public ResponseEntity<Appointment> createDentist(@RequestBody Appointment newAppointment) throws BadRequestException {
        // buscar odontologo o ¿ paciente ? si no existen lanzar exception sino todo ok
        if (!odontologo.isPresent() || !paciente.isPresent()) {
            throw new BadRequestException("blabla");
        }

        return new ResponseEntity<>(appointmentService.save(newAppointment), HttpStatus.CREATED);
    }*/
}
