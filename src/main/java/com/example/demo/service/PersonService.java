package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("firstDB") PersonDao personDao) {
        this.personDao = personDao;
    }

    public void addPerson(Person person) {
        personDao.createPerson(person);
    }

    public void updatePerson(UUID id, Person newPerson) {
        personDao.updatePerson(id, newPerson);
    }

    public void deletePersonByID(UUID id) {
        personDao.removePersonsByID(id);
    }

    public void deletePersonByEmail(String emailAddress) {
        personDao.removePersonsByEmail(emailAddress);
    }

    public void deleteAllPersons() {
        personDao.removePersons();
    }

    public List<Person> listAllPersons() {
        return personDao.selectAllPersons();
    }

    public Optional<Person> selectPersonByID(UUID id) {
        return personDao.selectPersonById(id);
    }
}
