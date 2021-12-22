package io.github.emfsilva.controller;

import io.github.emfsilva.data.vo.v1.PersonDTO;
import io.github.emfsilva.services.PersonServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@SuppressWarnings("ALL")
@Api(tags = "PersonEndpoint")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {


    private final  PersonServices service;
    private final  PagedResourcesAssembler<PersonDTO> assembler;

    @Autowired
    public PersonController(PersonServices service, PagedResourcesAssembler<PersonDTO> assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @ApiOperation(value = "Find all people")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PagedResources<PersonDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sorDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sorDirection, "firstName"));

        Page<PersonDTO> persons = service.findAll(pageable);
        persons.forEach(p -> p.add(
                linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return new ResponseEntity(assembler.toResource(persons), HttpStatus.OK);
    }


    @ApiOperation(value = "Find all people")
    @GetMapping(value = "/findPersonByName/{firstName}"  ,produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PagedResources<PersonDTO>> findPersonByName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sorDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sorDirection, "firstName"));

        Page<PersonDTO> persons = service.findPersonByName(firstName, pageable);
        persons.forEach(p -> p.add(
                linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return new ResponseEntity(assembler.toResource(persons), HttpStatus.OK);
    }

    @ApiOperation(value = "Find a specific person by your ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO findById(@PathVariable("id") Long id) {
        PersonDTO personDTO = service.findById(id);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personDTO;
    }

    @ApiOperation(value = "Create a new person")
    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO create(@RequestBody PersonDTO person) {
        PersonDTO personDTO = service.create(person);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());
        return personDTO;
    }

    @ApiOperation(value = "Update a specific person")
    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO update(@RequestBody PersonDTO person) {
        PersonDTO personDTO = service.update(person);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());
        return personDTO;
    }

    @ApiOperation(value = "Disable a specific person by your ID")
    @PatchMapping(value = "/disable/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO disablePerson(@PathVariable("id") Long id) {
        PersonDTO personDTO = service.disablePersons(id);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personDTO;
    }

    @ApiOperation(value = "Enable a specific person by your ID")
    @PatchMapping(value = "/enable/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO enablePerson(@PathVariable("id") Long id) {
        PersonDTO personDTO = service.enablePersons(id);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personDTO;
    }

    @ApiOperation(value = "Delete a specific person by your ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<PersonDTO> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}