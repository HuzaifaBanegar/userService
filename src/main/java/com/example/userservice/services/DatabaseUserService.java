package com.example.userservice.services;

import com.example.userservice.dtos.UserDTO;
import com.example.userservice.errorHandler.UserNotFound;
import com.example.userservice.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("databaseUserService")
public class DatabaseUserService implements UserService {
    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUser(Long userId) {
        return null;
    }

    @Override
    public User updateUser(Long userId, UserDTO partialUserDTO) throws UserNotFound {
        return null;
    }


    @Override
    public User createUser(Long userId, String username, String email, String password, String phone, String city, String street, int number, String zipcode, String firstname, String lastname) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
