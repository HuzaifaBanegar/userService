package com.example.userservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String email;
    private String username;
    private String password;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Name name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;
    private String phone;

    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Name getName() {
        return name;
    }
    public Address getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(Name name) {
        this.name = name;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
