package io.github.emfsilva.restfull.controllers;

import io.github.emfsilva.restfull.model.Person;
import io.github.emfsilva.restfull.services.PersonService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
         List<Person> persons = service.findAll();
        return ResponseEntity.ok().body(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable("id") Long id) {
        Person person = service.findById(id);
        return ResponseEntity.ok().body(person);
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person p = service.create(person);
        return ResponseEntity.ok().body(p);
    }

    @PutMapping
    public ResponseEntity<Person> update(@RequestBody Person person) {
        Person p = service.update(person);
        return ResponseEntity.ok().body(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
       return  ResponseEntity.ok().build();
    }
}
