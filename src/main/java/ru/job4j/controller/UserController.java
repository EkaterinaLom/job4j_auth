package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Person;
import ru.job4j.repository.PersonRepository;

import java.util.Collection;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private PersonRepository users;
    private PasswordEncoder encoder;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        users.save(person);
    }

    @GetMapping("/all")
    public Collection<Person> findAll() {
        return users.findAll();
    }
}
