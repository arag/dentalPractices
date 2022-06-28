package com.dh.beTFI.dentalPractices.model;

public class Dentist {
    private int id;
    private String lastName;
    private String firstName;
    private int professionalId;

    public Dentist(int id, String lastName, String firstName, int proffesionalId) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.professionalId = proffesionalId;
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

    public int getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(int professionalId) {
        this.professionalId = professionalId;
    }
}
