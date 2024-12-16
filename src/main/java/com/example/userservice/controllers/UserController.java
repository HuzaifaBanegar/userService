package com.example.userservice.controllers;

import com.example.userservice.dtos.UserDTO;
import com.example.userservice.errorHandler.UserNotFound;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;
    public UserController(@Qualifier("fakeApiUserService") UserService userService) {
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

    @PutMapping("users/{id}")
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
}
