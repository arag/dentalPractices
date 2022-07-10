package com.dh.beTFI.dentalPractices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "dentists")
public class Dentist {
    @Id
    @SequenceGenerator(name = "dentist_sequence", sequenceName = "dentist_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dentist_sequence")
    private Long id;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String firstname;

    @Column(name = "professional_license_number", nullable = false)
    private int professionalLicenseNumber;

    // default fetch = LAZY
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

    public String showDentistData() {
        String message = String.format("LICENSE NUMBER = %s, LASTNAME = %s, FIRSTNAME = %s",
                professionalLicenseNumber, lastname, firstname);

        if (id != null) {
            message = String.format("ID = %s ", id) + message;
        }

        return message;
    }
}
