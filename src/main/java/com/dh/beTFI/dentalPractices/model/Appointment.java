package com.dh.beTFI.dentalPractices.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @SequenceGenerator(name = "appointment_sequence", sequenceName = "appointment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dentist_id", nullable = false)
    private Dentist dentist;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;


    public Appointment() {
    }

    public Appointment(Dentist dentist, Patient patient, LocalDate appointmentDate) {
        this.dentist = dentist;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
    }

    public Appointment(Long id, Dentist dentist, Patient patient, LocalDate appointmentDate) {
        this.id = id;
        this.dentist = dentist;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
    }

    public Long getId() {
        return id;
    }

    /* public void setId(Long id) {
        this.id = id;
    } */

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String showAppointmentData() {
        String patientId = patient != null ? patient.getId().toString() : null;
        String dentistId = dentist != null ? dentist.getId().toString() : null;
        String message = String.format("PATIENT ID = %s, DENTIST ID = %s, APPOINTMENT DATE = %s",
                patientId, dentistId, appointmentDate);

        if (id != null) {
            message = String.format("ID = %s ", id) + message;
        }

        return message;
    }
}
