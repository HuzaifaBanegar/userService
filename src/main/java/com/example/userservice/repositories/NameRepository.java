package com.example.userservice.repositories;

import com.example.userservice.models.Name;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NameRepository extends JpaRepository<Name, Long> {
    Name save(Name name);
    void deleteById(Long Id);
}
