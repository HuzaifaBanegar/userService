package com.example.userservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address extends BaseModel {
    private String city;
    private String street;
    private int number;
    private String zipcode;


    public String getCity() {
        return city;
    }
    public String getStreet() {
        return street;
    }
    public int getNumber() {
        return number;
    }
    public String getZipcode() {
        return zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
