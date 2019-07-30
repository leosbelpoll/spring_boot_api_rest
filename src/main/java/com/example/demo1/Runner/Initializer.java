package com.example.demo1.Runner;

import com.example.demo1.Entity.Person;
import com.example.demo1.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class Initializer implements CommandLineRunner {


    @Autowired
    private PersonRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Persona 1", "Persona 2", "Persona 3").forEach(name ->
                repository.save(new Person(name, 10))
        );

        repository.findAll().forEach(System.out::println);
    }
}
