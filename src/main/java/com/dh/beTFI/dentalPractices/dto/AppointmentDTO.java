package com.dh.beTFI.dentalPractices.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDTO {
    private Long id;
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

    public AppointmentDTO(Long id, Long patientId, Long dentistId, LocalDate appointmentDate) {
        this.id = id;
        this.patientId = patientId;
        this.dentistId = dentistId;
        this.appointmentDate = appointmentDate;
    }

    public Long getId() {
        return id;
    }

    /*
    public void setId(Long id) {
        this.id = id;
    } */

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDentistId() {
        return dentistId;
    }

    public void setDentistId(Long dentistId) {
        this.dentistId = dentistId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
