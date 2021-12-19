package io.github.emfsilva.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import io.github.emfsilva.data.vo.v1.PersonDTO;
import io.github.emfsilva.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "PersonEndpoint")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	
/*	@ApiOperation(value = "Find all people" )
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<PersonDTO> findAll() {
		List<PersonDTO> persons =  service.findAll();
		persons
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return persons;
	}	*/

	@ApiOperation(value = "Find all people" )
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<PersonDTO> findAll(
			@RequestParam (value = "page", defaultValue = "0") int page,
			@RequestParam (value = "limit", defaultValue = "12") int limit) {

		Pageable pageable = PageRequest.of(page, limit);

		List<PersonDTO> persons =  service.findAll(pageable);
		persons
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return persons;
	}

	
	@ApiOperation(value = "Find a specific person by your ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonDTO findById(@PathVariable("id") Long id) {
		PersonDTO personDTO = service.findById(id);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personDTO;
	}

	@ApiOperation(value = "Create a new person") 
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonDTO create(@RequestBody PersonDTO person) {
		PersonDTO personDTO = service.create(person);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());
		return personDTO;
	}
	
	@ApiOperation(value = "Update a specific person")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonDTO update(@RequestBody PersonDTO person) {
		PersonDTO personDTO = service.update(person);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());
		return personDTO;
	}

	@ApiOperation(value = "Disable a specific person by your ID" )
	@PatchMapping(value = "/disable/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonDTO disablePerson(@PathVariable("id") Long id) {
		PersonDTO personDTO = service.disablePersons(id);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personDTO;
	}

	@ApiOperation(value = "Enable a specific person by your ID" )
	@PatchMapping(value = "/enable/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonDTO enablePerson(@PathVariable("id") Long id) {
		PersonDTO personDTO = service.enablePersons(id);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personDTO;
	}

	@ApiOperation(value = "Delete a specific person by your ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}	
	
}