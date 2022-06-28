package com.dh.beTFI.dentalPractices.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column
    private String email;

    @Column
    private int dni;

    @Column(name = "admission_date")
    private LocalDate admissionDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public Long getId() {
        return id;
    }

    /* public void setId(Long id) {
        this.id = id;
    } es mala práctica */

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
