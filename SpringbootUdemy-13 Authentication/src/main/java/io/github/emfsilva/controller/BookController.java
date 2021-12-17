package io.github.emfsilva.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import io.github.emfsilva.data.vo.v1.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.emfsilva.services.BookServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "BookEndpoint") 
@RestController
@RequestMapping("/api/book/v1")
public class BookController {
	
	@Autowired
	private BookServices service;
	
	@ApiOperation(value = "Find all books" )
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<BookDTO> findAll() {
		List<BookDTO> books =  service.findAll();
		books
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return books;
	}	
	
	@ApiOperation(value = "Find a specific book by your ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BookDTO findById(@PathVariable("id") Long id) {
		BookDTO bookDTO = service.findById(id);
		bookDTO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return bookDTO;
	}	
	
	@ApiOperation(value = "Create a new book")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BookDTO create(@RequestBody BookDTO book) {
		BookDTO bookDTO = service.create(book);
		bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getKey())).withSelfRel());
		return bookDTO;
	}
	
	@ApiOperation(value = "Update a specific book")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BookDTO update(@RequestBody BookDTO book) {
		BookDTO bookDTO = service.update(book);
		bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getKey())).withSelfRel());
		return bookDTO;
	}	
	
	@ApiOperation(value = "Delete a specific book by your ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}	
	
}