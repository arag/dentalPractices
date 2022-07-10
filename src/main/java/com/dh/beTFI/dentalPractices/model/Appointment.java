package com.dh.beTFI.dentalPractices.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @SequenceGenerator(name = "appointment_sequence", sequenceName = "appointment_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_sequence")
    private Long id;

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @ManyToOne
    @JoinColumn(name = "dentist_id", nullable = false)
    private Dentist dentist;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

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
