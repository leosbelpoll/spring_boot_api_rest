package com.example.demo1.Controller;

import com.example.demo1.Entity.Person;
import com.example.demo1.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @GetMapping("/")
    Collection<Person> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<?> find(@PathVariable Long id) {
        Optional<Person> group = repository.findById(id);
        return group.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    ResponseEntity<Person> create(@Valid @RequestBody Person person) throws URISyntaxException {
        Person result = repository.save(person);
        return ResponseEntity.created(new URI("/persons/" + result.getId()))
                .body(result);
    }

    @PutMapping("/{id}")
    ResponseEntity<Person> update(@Valid @RequestBody Person person) {
        Person result = repository.save(person);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
