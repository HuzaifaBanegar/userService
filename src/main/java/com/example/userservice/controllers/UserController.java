package com.example.userservice.controllers;

import com.example.userservice.dtos.CreateUserDTO;
import com.example.userservice.dtos.UserDTO;
import com.example.userservice.errorHandler.UserNotFound;
import com.example.userservice.models.Address;
import com.example.userservice.models.Name;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;
    public UserController(@Qualifier("databaseUserService") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id) throws UserNotFound {
        return userService.getUser(id);
    }
    //String username, String email, String password, String phone, String city, String street, int number, String zipcode, String firstname, String lastname
    @PostMapping("/users")
    public User createUser(@RequestBody CreateUserDTO userDto){

        return userService.createUser(userDto.getUsername(), userDto.getEmail(), userDto.getPassword(), userDto.getPhone(),
                userDto.getCity(), userDto.getStreet(), userDto.getNumber(), userDto.getZipcode(),
                userDto.getFirstname(), userDto.getLastname());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> partialUpdateUser(
            @PathVariable("id") Long id,
            @RequestBody UserDTO partialUserDTO
    ) {
        try {
            User updatedUser = userService.updateUser(id, partialUserDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable("id") Long id){
        return userService.deleteUser(id);

    }
}
