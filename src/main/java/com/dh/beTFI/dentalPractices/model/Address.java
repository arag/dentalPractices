package com.dh.beTFI.dentalPractices.model;

public class Address {
    private int id;
    private String street;
    private int number;
    private String city;
    private String province;

    public Address(int id, String street, int number, String city, String province) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.province = province;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
