package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @PutMapping(path = "id/{id}")
    public void updatePerson(@PathVariable("id") UUID id, @RequestBody Person newPerson) {
        personService.updatePerson(id, newPerson);
    }

    @DeleteMapping(path = "id/{id}")
    public void deletePersonByID(@PathVariable("id") UUID id) {
        personService.deletePersonByID(id);
    }

    @DeleteMapping(path = "mail/{mail}")
    public void deletePersonByEmail(@PathVariable("mail") String mail) {
        personService.deletePersonByEmail(mail);
    }

    @DeleteMapping
    public void deleteAllPersons() {
        personService.deleteAllPersons();
    }

    @GetMapping
    public List<Person> GetAllPersons() {
        return personService.listAllPersons();
    }

    @GetMapping({"id/{id}"})
    public Optional<Person> GetPersonByID(@PathVariable("id") UUID id) {
        return personService.selectPersonByID(id);
    }
}
