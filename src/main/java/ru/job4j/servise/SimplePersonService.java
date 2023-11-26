package ru.job4j.servise;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.model.Person;
import ru.job4j.repository.PersonRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SimplePersonService implements PersonService {

    private static final Logger LOG = (Logger) LoggerFactory.getLogger(SimplePersonService.class.getName());
    private final PersonRepository persons;

    public SimplePersonService(PersonRepository persons) {
        this.persons = persons;
    }

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
        try {
            return Optional.of(persons.save(person));
        } catch (Exception e) {
            LOG.info("Person not save");
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Person person) {
        if (persons.existsById(person.getId())) {
            persons.save(person);
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(int id) {
        if (persons.existsById(id)) {
            persons.deleteById(id);
        }
    }
}
