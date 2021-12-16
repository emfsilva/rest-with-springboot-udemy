package io.github.emfsilva.restfull.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import io.github.emfsilva.restfull.data.dto.v1.PersonDTO;
import io.github.emfsilva.restfull.data.model.Person;
import io.github.emfsilva.restfull.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Person Endpoint", description = "Description for person", tags = {"Person Endpoint"})
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    private PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @ApiOperation(value = "Find all person record")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<PersonDTO> findAll() {
        List<PersonDTO> persons = service.findAll();
        persons.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    @ApiOperation(value = "Find just a person to ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO findById(@PathVariable("id") Long id) {
       PersonDTO personDTO = service.findById(id);
       personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
       return  personDTO;

    }

    @ApiOperation(value = "Create a new person record")
    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
                 consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO create(@RequestBody PersonDTO person) {
        PersonDTO personDTO = service.create(person);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());
        return personDTO;
    }

    @ApiOperation(value = "Update person")
    @PutMapping(produces = {"application/json", "application/xml","application/x-yaml"},
                consumes = {"application/json", "application/xml","application/x-yaml"})
    public PersonDTO update(@RequestBody PersonDTO person) {
        PersonDTO personDTO = service.update(person);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());
        return personDTO;
    }

    @ApiOperation(value = "Delete person")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
