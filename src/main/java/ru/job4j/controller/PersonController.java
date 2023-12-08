package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.model.Operation;
import ru.job4j.model.Person;
import ru.job4j.model.PersonDTO;
import ru.job4j.servise.PersonService;

import javax.validation.constraints.Positive;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService persons;
    private final PasswordEncoder encoder;

    @GetMapping("/")
    public Collection<Person> findAll() {
        return this.persons.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@Positive @PathVariable int id) {
        return this.persons.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Person not found, check the details"));
    }

    @PostMapping("/")
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Person> create(@Validated(Operation.OnCreate.class) @RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        return this.persons.save(person)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @PutMapping("/")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Void> update(@Validated(Operation.OnUpdate.class) @RequestBody Person person) {
        var updated = persons.update(person);
        if (updated) {
            return ResponseEntity.ok().build();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        var deleted = persons.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person is not deleted");
    }

    @PatchMapping("/{id}/{login}")
    public ResponseEntity<Person> updatePersonPartially(
            @Positive @PathVariable(value = "id") Integer id,
            @Validated(Operation.OnUpdate.class) @RequestBody PersonDTO personDetails)
            throws UsernameNotFoundException {
        var person = persons.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found on :: " + id));
        person.setLogin(personDetails.getLogin());
        final var updatedPerson = persons.update(person);
        if (updatedPerson) {
            return ResponseEntity.ok().build();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not updated");
    }
}
