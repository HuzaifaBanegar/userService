package com.example.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String phone;
    private String city;
    private String street;
    private int number;
    private String zipcode;

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getPhone() {
        return phone;
    }
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
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setPhone(String phone) {
        this.phone = phone;
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
