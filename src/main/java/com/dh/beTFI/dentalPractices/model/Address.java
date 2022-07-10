package com.dh.beTFI.dentalPractices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence")
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
}
