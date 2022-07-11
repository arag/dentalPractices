package com.dh.beTFI.dentalPractices.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDTO {
    private Long id;
    private Long patientId;
    private Long dentistId;
    private String appointmentDate;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Long patientId, Long dentistId, String appointmentDate) {
        this.patientId = patientId;
        this.dentistId = dentistId;
        this.appointmentDate = appointmentDate;
    }

    public AppointmentDTO(Long id, Long patientId, Long dentistId, String appointmentDate) {
        this.id = id;
        this.patientId = patientId;
        this.dentistId = dentistId;
        this.appointmentDate = appointmentDate;
    }
}
