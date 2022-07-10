package com.dh.beTFI.dentalPractices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @SequenceGenerator(name = "patient_sequence", sequenceName = "patient_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_sequence")
    private Long id;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int dni;

    @Column(name = "admission_date")
    private LocalDate admissionDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
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

    public Patient(Long id) {
        this.id = id;
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

    public String showPatientData() {
        String addressData = address != null
                ? String.format("STREET = %s, NUMBER = %s, CITY = %s, PROVINCE = %s",
                address.getStreet(), address.getNumber(), address.getCity(), address.getProvince())
                : null;
        String message = String.format("DNI = %s, LASTNAME = %s, FIRSTNAME = %s, EMAIL = %s, ADMISSION DATE = %s, ADDRESS: %s",
                dni, lastname, firstname, email, admissionDate.toString(), addressData);

        if (id != null) {
            message = String.format("ID = %s ", id) + message ;
        }

        return message;
    }
}
