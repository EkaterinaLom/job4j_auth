package ru.job4j.servise;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.job4j.model.Person;
import java.util.Collection;
import java.util.Optional;

public interface PersonService extends UserDetailsService {

    Collection<Person> findAll();

    Optional<Person> findById(int id);

    Optional<Person> save(Person person);

    boolean update(Person person);

    boolean deleteById(int id);

    Optional<Person> findByLogin(String login);
}
