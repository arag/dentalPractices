package com.dh.beTFI.dentalPractices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @SequenceGenerator(name = "patient_sequence", sequenceName = "patient_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_sequence")
    private Long id;

    @Column
    private String lastname;

    @Column
    private String firstname;

    @Column
    private String email;

    @Column
    private int dni;

    @Column(name = "admission_date")
    private LocalDate admissionDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<>();

    public Patient() {
    }

    public Patient(String lastname, String firstname, String email, int dni, LocalDate admissionDate, Address address) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.dni = dni;
        this.admissionDate = admissionDate;
        this.address = address;
    }

    public Patient(Long id, String lastname, String firstname, String email, int dni, LocalDate admissionDate, Address address) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.dni = dni;
        this.admissionDate = admissionDate;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    /* public void setId(Long id) {
        this.id = id;
    } es mala práctica */

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String showPatientData() {
        String addressData = String.format("STREET = %s, NUMBER = %s, CITY = %s, PROVINCE = %s",
                address.getStreet(), address.getNumber(), address.getCity(), address.getProvince());
        String message = String.format("DNI = %s, LASTNAME = %s, FIRSTNAME = %s, EMAIL = %s, ADMISSION DATE = %s, ADDRESS: %s",
                dni, lastname, firstname, email, admissionDate.toString(), addressData);

        if (id != null) {
            message = String.format("ID = %s ", id) + message ;
        }

        return message;
    }
}
