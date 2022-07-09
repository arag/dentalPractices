package com.dh.beTFI.dentalPractices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    private Long id;

    @Column
    private String street;

    @Column
    private int number;

    @Column
    private String city;

    @Column
    private String province;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Patient patient;

    public Address() {}

    public Address(String street, int number, String city, String province) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.province = province;
    }

    public Address(Long id, String street, int number, String city, String province) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.province = province;
    }

    public Long getId() {
        return id;
    }

    /* public void setId(Long id) {
        this.id = id;
    }*/

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
