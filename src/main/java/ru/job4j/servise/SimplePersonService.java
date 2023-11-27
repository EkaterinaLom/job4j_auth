package ru.job4j.servise;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Person;
import ru.job4j.repository.PersonRepository;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimplePersonService implements PersonService {

    private final PersonRepository persons;

    @Override
    public Collection<Person> findAll() {
        return persons.findAll();
    }

    @Override
    public Optional<Person> findById(int id) {
        return persons.findById(id);
    }

    @Override
    public Optional<Person> save(Person person) {
        Optional<Person> rsl = Optional.empty();
        try {
            rsl = Optional.of(persons.save(person));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean update(Person person) {
        boolean updated = false;
        try {
            persons.save(person);
            updated = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public boolean deleteById(int id) {
        boolean deleted = false;
        try {
            persons.deleteById(id);
            deleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }
}
