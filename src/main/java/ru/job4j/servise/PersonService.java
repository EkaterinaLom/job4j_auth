package ru.job4j.servise;

import ru.job4j.model.Person;
import java.util.Collection;
import java.util.Optional;

public interface PersonService {

    Collection<Person> findAll();

    Optional<Person> findById(int id);

    Optional<Person> save(Person person);

    boolean update(Person person);

    boolean deleteById(int id);
}
