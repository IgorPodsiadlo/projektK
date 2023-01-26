package com.example.projektk.repository;

import com.example.projektk.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findAllByFirstName(String firstName);
}
