package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Person;
import ru.job4j.servise.SimplePersonService;
import java.util.Collection;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final SimplePersonService persons;

    public PersonController(SimplePersonService persons) {
        this.persons = persons;
    }

    @GetMapping("/")
    public Collection<Person> findAll() {
        return this.persons.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        var person = this.persons.findById(id);
        return new ResponseEntity<Person>(
                person.orElse(new Person()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return persons.save(person)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        return persons.update(person) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        persons.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
