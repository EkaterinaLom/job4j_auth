package ru.job4j.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
public class PersonDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "Id must be Positive")
    private int id;
    @Pattern(regexp = ".{5,9}", message = "The login must be from 5 to 9 characters")
    private String login;
}
