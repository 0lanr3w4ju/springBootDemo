package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    void createPerson(UUID id, Person person);

    default void createPerson(Person person) {
        UUID id = UUID.randomUUID();
        createPerson(id, person);
    }

    void updatePerson(UUID id, Person person);

    void removePersons();

    void removePersonsByID(UUID id);

    void removePersonsByEmail(String emailAddress);

    List<Person> selectAllPersons();

    Optional<Person> selectPersonById(UUID id);
}
