package com.example.userservice.dtos;

import com.example.userservice.models.Address;
import com.example.userservice.models.Name;
import com.example.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String email;
    private String username;
    private String password;
    private Name name;
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

    public void updateFrom(UserDTO partialUser) {
        // Update simple fields if they are not null
        if (partialUser.email != null) {
            this.email = partialUser.email;
        }
        if (partialUser.username != null) {
            this.username = partialUser.username;
        }
        if (partialUser.password != null) {
            this.password = partialUser.password;
        }
        if (partialUser.phone != null) {
            this.phone = partialUser.phone;
        }

        // Update nested address if not null
        if (partialUser.address != null) {
            if (this.address == null) {
                this.address = new Address();
            }
            if (partialUser.address.getCity() != null) {
                this.address.setCity(partialUser.address.getCity());
            }
            if (partialUser.address.getStreet() != null) {
                this.address.setStreet(partialUser.address.getStreet());
            }
            if (partialUser.address.getNumber() != 0) {
                this.address.setNumber(partialUser.address.getNumber());
            }
            if (partialUser.address.getZipcode() != null) {
                this.address.setZipcode(partialUser.address.getZipcode());
            }
        }

        // Update nested name if not null
        if (partialUser.name != null) {
            if (this.name == null) {
                this.name = new Name();
            }
            if (partialUser.name.getFirstname() != null) {
                this.name.setFirstname(partialUser.name.getFirstname());
            }
            if (partialUser.name.getLastname() != null) {
                this.name.setLastname(partialUser.name.getLastname());
            }
        }
    }

    public User toUser(){
        User user = new User();
        user.setEmail(getEmail());
        user.setUsername(getUsername());
        user.setPassword(getPassword());
        user.setPhone(getPhone());
        user.setAddress(getAddress());
        user.setName(getName());

        return user;


    }
}
