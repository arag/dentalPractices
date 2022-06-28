package com.dh.beTFI.dentalPractices.dto;

import java.time.LocalDate;

public class PatientDTO {
    private int id;
    private String lastName;
    private String firstName;
    private String email;
    private int dni;
    private LocalDate admissionDate;

    public PatientDTO() {
    }

    public PatientDTO(int id, String lastName, String firstName, String email, int dni, LocalDate admissionDate) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.dni = dni;
        this.admissionDate = admissionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }
}
