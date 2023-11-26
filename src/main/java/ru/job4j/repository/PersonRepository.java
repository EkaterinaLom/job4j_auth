package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Person;
import java.util.Collection;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Override
    Collection<Person> findAll();
}