package com.example.userservice.repositories;

import com.example.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    List<User> findAll();
    Optional<User> findUserById(Long id);
    User deleteUserById(Long id);
}
