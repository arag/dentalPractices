package com.dh.beTFI.dentalPractices.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDTO {
    private Long patientId;
    private Long dentistId;
    private LocalDate appointmentDate;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Long patientId, Long dentistId, LocalDate appointmentDate) {
        this.patientId = patientId;
        this.dentistId = dentistId;
        this.appointmentDate = appointmentDate;
    }
}
