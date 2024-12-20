package com.example.userservice.services;

import com.example.userservice.dtos.UserDTO;
import com.example.userservice.errorHandler.UserNotFound;
import com.example.userservice.models.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();
    public User getUser(Long userId) throws UserNotFound;
    public User updateUser(Long userId, UserDTO partialUserDTO) throws UserNotFound;
    public User createUser(String username, String email, String password, String phone, String city, String street, int number, String zipcode, String firstname, String lastname);
    public User deleteUser(Long userId);
}
