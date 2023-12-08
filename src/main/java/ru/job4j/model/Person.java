package ru.job4j.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"login"}))
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "Id must be Positive", groups = {Operation.OnUpdate.class})
    private int id;

    @NotBlank(message = "Login must be not empty", groups = {Operation.OnUpdate.class, Operation.OnCreate.class})
    private String login;

    @Pattern(regexp = ".{5,9}", message = "The password must be from 5 to 9 characters", groups = {Operation.
            OnUpdate.class, Operation.OnCreate.class})
    @NotBlank(message = "Password must be not empty", groups = {Operation.OnUpdate.class, Operation.OnCreate.class})
    private String password;
}