package com.dh.beTFI.dentalPractices.model;

import java.time.LocalDate;

public class Appointment {
    private int id;
    private Dentist dentist;
    private Patient patient;
    private LocalDate dateTime;

    public Appointment() {}

    public Appointment(Dentist dentist, Patient patient, LocalDate dateTime) {
        this.dentist = dentist;
        this.patient = patient;
        this.dateTime = dateTime;
    }

    public Appointment(int id, Dentist dentist, Patient patient, LocalDate dateTime) {
        this.id = id;
        this.dentist = dentist;
        this.patient = patient;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }
}
