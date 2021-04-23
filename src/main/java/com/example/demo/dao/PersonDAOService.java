package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("firstDB")
public class PersonDAOService implements PersonDao{

    private final ArrayList<Person> DB = new ArrayList<>();
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAOService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
//    public void createPerson(UUID id, Person person) {
//        DB.add(new Person(id, person.getName(), person.getEmailAddress()));
//    }

    public void createPerson(UUID id, Person person) {
        final String sql = "INSERT INTO person (id, name, emailAddress) VALUES (gen_random_uuid(), ?, ?)";


//        jdbcTemplate.update(sql);
    }

    @Override
    public void updatePerson(UUID id, Person newPerson) {
        Optional<Person> person = selectPersonById(id);
        Person person1 = null;

        if (person.isPresent()) {
            person1 = person.get();
        }

        int indexOfPersons = DB.indexOf(person1);

        DB.set(indexOfPersons, new Person(id, newPerson.getName(), newPerson.getEmailAddress()));
    }

    @Override
    public void removePersons() {
        DB.clear();
    }

    @Override
    public void removePersonsByID(UUID id) {
        Person personToBeRemoved = selectPersonById(id).get();
        DB.remove(personToBeRemoved);
    }

    @Override
    public void removePersonsByEmail(String emailAddress) {
        Optional<Person> personToRemove =
                DB.stream()
                .filter(person -> person.getEmailAddress().equals(emailAddress))
                .findFirst();

        Person personToBeRemoved = personToRemove.get();

        DB.remove(personToBeRemoved);
    }

    @Override
    public List<Person> selectAllPersons() {
        final String sql = "SELECT * FROM person"; // sql command for query
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String emailAddress = resultSet.getString("emailAddress");

            return new Person(id, name, emailAddress);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT * FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID personID = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("emailAddress");
                    return new Person(personID, name, email);
                });
        return Optional.ofNullable(person);
    }
}
