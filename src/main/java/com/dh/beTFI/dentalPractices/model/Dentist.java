package com.dh.beTFI.dentalPractices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dentists")
public class Dentist {
    @Id
    @SequenceGenerator(name = "dentist_sequence", sequenceName = "dentist_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dentist_sequence")
    private Long id;

    @Column
    private String lastname;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "professional_license_number")
    private int professionalLicenseNumber;

    @OneToMany(mappedBy = "dentist")
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<>();

    public Dentist() {
    }

    public Dentist(String lastname, String firstname, int professionalLicenseNumber) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.professionalLicenseNumber = professionalLicenseNumber;
    }

    public Dentist(Long id) {
        this.id = id;
    }

    public Dentist(Long id, String lastname, String firstname, int professionalLicenseNumber) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.professionalLicenseNumber = professionalLicenseNumber;
    }

    public Long getId() {
        return id;
    }

    /* public void setId(Long id) {
        this.id = id;
    } */

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

    public int getProfessionalLicenseNumber() {
        return professionalLicenseNumber;
    }

    public void setProfessionalLicenseNumber(int professionalLicenseNumber) {
        this.professionalLicenseNumber = professionalLicenseNumber;
    }

    public String showDentistData() {
        String message = String.format("LICENSE NUMBER = %s, LASTNAME = %s, FIRSTNAME = %s",
                professionalLicenseNumber, lastname, firstname);

        if (id != null) {
            message = String.format("ID = %s ", id) + message;
        }

        return message;
    }
}
