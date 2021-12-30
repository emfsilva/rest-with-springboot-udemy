package io.github.emfsilva.controller;

import io.github.emfsilva.data.vo.v1.PersonDTO;
import io.github.emfsilva.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "PersonEndpoint")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {


    private final PersonServices service;

    @Autowired
    public PersonController(PersonServices service) {
        this.service = service;
    }

    @Operation(summary = "Find all people")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<CollectionModel<PersonDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sorDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sorDirection, "firstName"));

        Page<PersonDTO> persons = service.findAll(pageable);
        persons.forEach(p -> p.add( linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return ResponseEntity.ok(CollectionModel.of(persons));
    }


    @Operation(summary = "Find all people")
    @GetMapping(value = "/findPersonByName/{firstName}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<CollectionModel<PersonDTO>> findPersonByName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sorDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sorDirection, "firstName"));

        Page<PersonDTO> persons = service.findPersonByName(firstName, pageable);
        persons.forEach(p -> p.add(
                linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

        Link findAllLink = linkTo(methodOn(PersonController.class).findAll(page,limit,direction)).withSelfRel();

        return ResponseEntity.ok(CollectionModel.of(persons, findAllLink));
    }

    @Operation(summary = "Find a specific person by your ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO findById(@PathVariable("id") Long id) {
        PersonDTO personDTO = service.findById(id);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personDTO;
    }

    @Operation(summary = "Create a new person")
    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO create(@RequestBody PersonDTO person) {
        PersonDTO personDTO = service.create(person);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());
        return personDTO;
    }

    @Operation(summary = "Update a specific person")
    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO update(@RequestBody PersonDTO person) {
        PersonDTO personDTO = service.update(person);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());
        return personDTO;
    }

    @Operation(summary = "Disable a specific person by your ID")
    @PatchMapping(value = "/disable/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO disablePerson(@PathVariable("id") Long id) {
        PersonDTO personDTO = service.disablePersons(id);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personDTO;
    }

    @Operation(summary = "Enable a specific person by your ID")
    @PatchMapping(value = "/enable/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO enablePerson(@PathVariable("id") Long id) {
        PersonDTO personDTO = service.enablePersons(id);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personDTO;
    }

    @Operation(summary = "Delete a specific person by your ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<PersonDTO> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}